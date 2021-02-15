package org.acme.hibernate.orm.panache;

import io.quarkus.panache.common.Sort;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class PersonTest {

    @BeforeAll
    @Transactional
    public static void setup() {
        Country foo = new Country();
        foo.name = "Foo";
        foo.persist();

        Person alice = new Person();
        alice.name = "Alice";
        alice.birth = LocalDate.of(1910, Month.FEBRUARY, 1);
        alice.country = foo;
        alice.persist();

        Person bob = new Person();
        bob.name = "Bob";
        bob.birth = LocalDate.of(1920, Month.MARCH, 1);
        bob.persist();
    }

    @Test
    public void findAllSortByNameTest() {
        List<Person> allPersons = Person.listAll(Sort.by("name"));
        assertEquals(2, allPersons.size());
    }

    @Test
    public void findAllSortByCountryNameTest() {
        List<Person> allPersons = Person.listAll(Sort.by("country.name"));
        assertEquals( 2, allPersons.size());
    }
}
