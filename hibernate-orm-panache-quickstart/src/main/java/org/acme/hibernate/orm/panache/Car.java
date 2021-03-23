package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Car extends PanacheEntity {
    public String model;
    public String maker;
    public Integer year;
    @ManyToOne
    public Person owner;
}