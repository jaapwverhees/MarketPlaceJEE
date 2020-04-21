package com.controller.DAO;

import com.model.Visitor;
import com.util.password.PasswordAuthentication;

import javax.persistence.EntityManager;

public class VisitorDAO implements VisitorDAOable {

    private final PasswordAuthentication aut = new PasswordAuthentication();

    private final EntityManager entityManager;

    public VisitorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Visitor getRegisteredVisitor(String email) throws Exception {
        return entityManager.find(Visitor.class, email);
    }

    @Override
    public void addRegistredVisitor(Visitor visitor) throws Exception {
        visitor.setPassword(aut.hash(visitor.getPassword()));
        entityManager.getTransaction().begin();
        entityManager.persist(visitor);
        entityManager.getTransaction().commit();
    }
}
