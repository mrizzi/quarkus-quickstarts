package org.acme.hibernate.orm.panache;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class PersonEndpointTest {

    @BeforeAll
    @Transactional
    public static void setup() {
        Country foo = new Country();
        foo.name = "Foo";
        foo.persist();
    }
    
    @Test
    public void test() {
        // Create a Person with a associated Country
        final Person alice = new Person();
        alice.name = "Alice";
        alice.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        final Country foo = new Country();
        foo.id = 4L;
        alice.country = foo; 
        alice.id = Long.valueOf(given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(alice)
            .when()
            .post("/person")
            .then()
            .statusCode(201)
            .extract()
            .path("id")
            .toString());

        // Update 'alice' removing the 'country'
        alice.country = null;
        given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("id", alice.id)
            .body(alice)
            .when()
            .put("/person/{id}")
            .then()
            .statusCode(204);

        // Read 'alice' from REST endpoint to check
        given()
            .accept(ContentType.JSON)
            .pathParam("id", alice.id)
            .when()
            .get("/person/{id}")
            .then()
            .statusCode(200)
            .body("country", is(emptyOrNullString()));
    }
    
    @Test
    @Transactional
    public void testDb() {
        // Create a Person with a associated Country
        final Person alice = new Person();
        alice.name = "Alice";
        alice.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        final Country foo = new Country();
        foo.id = 4L;
        alice.country = foo;
        alice.persistAndFlush();

        List<Person> people = Person.find("name = 'Alice'").list();
        assertNotNull(people.get(0).country);

        alice.country = null;
        alice.persistAndFlush();

        List<Person> peopleUpdated = Person.find("name = 'Alice'").list();
        assertNull(peopleUpdated.get(0).country);
    }
}
