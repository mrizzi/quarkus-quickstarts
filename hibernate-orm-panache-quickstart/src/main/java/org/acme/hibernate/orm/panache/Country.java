package org.acme.hibernate.orm.panache;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Country extends PanacheEntity {
    public String name;
    public Long population;
    @OneToMany(mappedBy = "country", fetch = FetchType.LAZY)
    @JsonBackReference
    public List<Person> people = new ArrayList<>();
}
