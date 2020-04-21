package com.controller;

import com.controller.DAO.VisitorDAO;
import com.controller.DAO.VisitorDAOable;
import com.model.Visitor;
import com.util.password.PasswordAuthentication;

import javax.persistence.Persistence;


public class VisitorLoginController {

    VisitorDAOable registeredVisitorDAO = new VisitorDAO(Persistence.createEntityManagerFactory("MySQL").createEntityManager());
    PasswordAuthentication aut = new PasswordAuthentication();

    //TODO data transfer object?
    //TODO return map with object and message?
    public Visitor login(String email, String password) {
        try {
            Visitor registeredVisitor = registeredVisitorDAO.getRegisteredVisitor(email);
            if (aut.authenticate(password.toCharArray(), registeredVisitor.getPassword())) {
                registeredVisitor.setPassword(password);
                return registeredVisitor;
            }
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }

    //TODO setters are for testing, find way to test without setters
    public void setRegisteredVisitorDAO(VisitorDAOable registeredVisitorDAO) {
        this.registeredVisitorDAO = registeredVisitorDAO;
    }
}
