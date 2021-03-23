package org.acme.hibernate.orm.panache;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.time.LocalDate;
import java.util.HashSet;
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
    
}
