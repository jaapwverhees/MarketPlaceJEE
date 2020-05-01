package com.controller;

import com.controller.DAO.VisitorDAO;
import com.controller.DAO.VisitorDAOable;
import com.model.Visitor;
import com.util.password.PasswordAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.persistence.Persistence;


public class VisitorLoginController {

    private Logger errorLog = LoggerFactory.getLogger(this.getClass());

    private VisitorDAOable visitorDAO = new VisitorDAO();

    private PasswordAuthentication aut = new PasswordAuthentication();

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
}