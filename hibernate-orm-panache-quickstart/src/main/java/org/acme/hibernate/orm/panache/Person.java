package org.acme.hibernate.orm.panache;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;

    @ManyToOne
    public Country country;

    @ManyToMany(mappedBy = "persons", fetch = FetchType.LAZY)
//    @Transient
    @JsonBackReference("fruitsReference")
    public Set<Fruit> fruits = new HashSet<>();

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
//    @Transient
    public Set<Car> cars = new HashSet<>();

    @PostPersist
    private void postPersist() {
        // using the iterator lets us remove the not-valid referenced Fruits IDs
        Iterator<Fruit> iter = fruits.iterator();
        while (iter.hasNext()) {
            Fruit fruitFromDb = Fruit.findById(iter.next().id);
            if (fruitFromDb != null) fruitFromDb.persons.add(this);
            else iter.remove();
        }
    }
}
