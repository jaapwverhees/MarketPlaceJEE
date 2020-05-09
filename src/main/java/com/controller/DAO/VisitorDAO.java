package com.controller.DAO;

import com.model.Visitor;
import com.util.exeptions.CustomException;
import com.util.password.PasswordAuthentication;
import com.util.producers.EntityManagerProducer;

import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

public class VisitorDAO {

    private final PasswordAuthentication aut = new PasswordAuthentication();

    private EntityManager entityManager = EntityManagerProducer.getEntityManager();

    public VisitorDAO() {}

    public VisitorDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    public Visitor getVisitor(String email) {
        return entityManager.find(Visitor.class, email);
    }

    public void createVisitor(Visitor visitor) throws Exception {

        visitor.setPassword(aut.hash(visitor.getPassword()));
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(visitor);
            entityManager.getTransaction().commit();
        }catch(RollbackException e){
            throw new CustomException("Emailadres is al in gebruik");
        }finally {
            entityManager.detach(visitor);
        }
    }
}
