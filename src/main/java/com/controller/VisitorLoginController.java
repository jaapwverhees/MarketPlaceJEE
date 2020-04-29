package com.controller;

import com.controller.DAO.VisitorDAOable;
import com.model.Visitor;
import com.util.password.PasswordAuthentication;
import org.slf4j.Logger;
import javax.inject.Inject;


public class VisitorLoginController {

    @Inject
    private Logger errorLog;

    @Inject
    private VisitorDAOable visitorDAO;

    @Inject
    private PasswordAuthentication aut;

    public Visitor login(String email, String password) {
        try {
            Visitor registeredVisitor = visitorDAO.getVisitor(email);
            if (aut.authenticate(password.toCharArray(), registeredVisitor.getPassword())) {
                registeredVisitor.setPassword(password);
                return registeredVisitor;
            }
        } catch (Exception e) {
            errorLog.error("Error", e);
        }
        return null;
    }

    //TODO setters are for testing, find way to test without setters
    public void setVisitorDAO(VisitorDAOable visitorDAO) {
        this.visitorDAO = visitorDAO;
    }
}