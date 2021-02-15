package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;

@Entity
public class Country extends PanacheEntity {
    public String name;
    public Long population;
}
