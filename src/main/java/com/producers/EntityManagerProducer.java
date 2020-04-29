package com.producers;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerProducer {

    @Produces
    public static EntityManager mysql() {
        return Persistence.createEntityManagerFactory("MySQL").createEntityManager();
    }

}
