package org.acme.hibernate.orm.panache;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import java.util.HashSet;
import java.util.Set;

@Entity
@Cacheable
public class Fruit extends PanacheEntity {

    @Column(length = 40, unique = true)
    public String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "fruit_person",
            joinColumns = {@JoinColumn(name = "fruit_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    public Set<Person> persons = new HashSet<>();

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }
}
