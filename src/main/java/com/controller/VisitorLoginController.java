package com.controller;

import com.controller.DAO.VisitorDAO;
import com.model.Visitor;
import com.util.password.PasswordAuthentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class VisitorLoginController {

    @SuppressWarnings("FieldMayBeFinal")
    private Logger errorLog = LoggerFactory.getLogger(this.getClass());


    @SuppressWarnings("FieldMayBeFinal")
    private VisitorDAO visitorDAO = new VisitorDAO();


    @SuppressWarnings("FieldMayBeFinal")
    private PasswordAuthentication aut = new PasswordAuthentication();

    public Visitor login(String email, String password) {
        try {
            Visitor registeredVisitor = visitorDAO.getVisitor(email);
            if (aut.authenticate(password.toCharArray(), registeredVisitor.getPassword())) {
                registeredVisitor.setPassword(password);
                return registeredVisitor;
            }
        } catch (Exception e) {
            errorLog.error("ERROR :", e);
        }
        return null;
    }
}