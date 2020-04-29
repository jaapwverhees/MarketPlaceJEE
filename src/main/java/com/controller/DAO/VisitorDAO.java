package com.controller.DAO;

import com.model.Visitor;
import com.util.password.PasswordAuthentication;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class VisitorDAO implements VisitorDAOable {

    //@Inject
    private PasswordAuthentication aut = new PasswordAuthentication();

    //@Inject
    private EntityManager entityManager = Persistence.createEntityManagerFactory("MySQL").createEntityManager();


    @Override
    public Visitor getVisitor(String email) throws Exception {
        return entityManager.find(Visitor.class, email);
    }

    @Override
    public void createVisitor(Visitor visitor) throws Exception {
        visitor.setPassword(aut.hash(visitor.getPassword()));
        entityManager.getTransaction().begin();
        entityManager.persist(visitor);
        entityManager.getTransaction().commit();
    }

}
