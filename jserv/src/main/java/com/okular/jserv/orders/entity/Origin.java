package com.okular.jserv.orders.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import static com.okular.jserv.orders.entity.Origin.FIND_ALL;

@Entity
@Table(name = "origins")
@NamedQuery(name = FIND_ALL, query = "select o from Origin o")
public class Origin {

    public static final String FIND_ALL = "Origin.findAll";

    @Id
    private String name;

    public Origin() {
    }

    public Origin(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
