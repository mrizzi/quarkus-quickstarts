package org.acme.hibernate.orm.panache;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;

@ResourceProperties(hal = true)
public interface PersonResource extends PanacheEntityResource<Person, Long> {}
