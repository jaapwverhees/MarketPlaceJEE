package com.controller.DAO;

import com.model.RegistredVisitor;

interface RegisteredVisitorDAOable {

    public RegistredVisitor getRegisteredVisitor(String email) throws Exception;

    public void addRegistredVisitor(RegistredVisitor visitor) throws Exception;


}
