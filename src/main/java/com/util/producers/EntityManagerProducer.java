package com.util.producers;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

@Singleton
public class EntityManagerProducer {

    private static final EntityManager entityManager = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

    public static EntityManager getEntityManager() {
        return entityManager;
    }
}
