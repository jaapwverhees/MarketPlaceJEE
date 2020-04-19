package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.model.RegistredVisitor;
import com.util.password.PasswordAuthentication;


//TODO create logincontroller functionality
public class VisitorLoginController {

    RegisteredVisitorDAOable registeredVisitorDAO = new RegisteredVisitorDAO();
    PasswordAuthentication aut = new PasswordAuthentication();

    //TODO data transfer object?
    //TODO return map with object and message?
    public RegistredVisitor login(String email, String password) {
        try {
            RegistredVisitor registeredVisitor = registeredVisitorDAO.getRegisteredVisitor(email);
            if(aut.authenticate(password.toCharArray(), registeredVisitor.getPassword())){
                registeredVisitor.setPassword(password);
                return registeredVisitor;
            }
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }

    //TODO setters are for testing, find way to test without setters
    public void setRegisteredVisitorDAO(RegisteredVisitorDAOable registeredVisitorDAO) {
        this.registeredVisitorDAO = registeredVisitorDAO;
    }
}
