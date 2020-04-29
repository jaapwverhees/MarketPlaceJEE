package com.controller.DAO;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
@Alternative
public class EntityManagerProducerAlt {

    @Produces
    public static EntityManager h2() {
        return Persistence.createEntityManagerFactory("H2").createEntityManager();
    }
}
