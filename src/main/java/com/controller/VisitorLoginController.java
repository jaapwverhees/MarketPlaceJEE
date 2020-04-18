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
    //todo return map with object and message?
    public RegistredVisitor login(String email, String password) {
        try {
            RegistredVisitor registeredVisitor = registeredVisitorDAO.getRegisteredVisitor(email);
            if(aut.authenticate(password.toCharArray(), registeredVisitor.getPassword())){
                return registeredVisitor;
            }
        } catch (Exception exception) {
            exception.printStackTrace();

        }
        return null;
    }
}
