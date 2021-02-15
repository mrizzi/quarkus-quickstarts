package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
    @ManyToOne
    public Country country;
}
