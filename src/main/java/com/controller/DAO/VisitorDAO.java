package com.controller.DAO;

import com.model.Visitor;
import com.util.exeptions.CustomException;
import com.util.password.PasswordAuthentication;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.RollbackException;

@Stateless
public class VisitorDAO {

    private final PasswordAuthentication aut = new PasswordAuthentication();

    @PersistenceContext(unitName = "MyPersistenceUnit")
    private EntityManager entityManager;

    public VisitorDAO() {}

    public Visitor getVisitor(String email) {
        return entityManager.find(Visitor.class, email);
    }

    public void createVisitor(Visitor visitor) throws Exception {

        //FIXME
        visitor.setPassword(aut.hash(visitor.getPassword()));
        try {
            // entityManager.getTransaction().begin();
            entityManager.persist(visitor);
            // entityManager.getTransaction().commit();
        } catch (RollbackException e) {
            throw new CustomException("Emailadres is al in gebruik");
        } finally {
            // entityManager.detach(visitor);
        }
    }
}
