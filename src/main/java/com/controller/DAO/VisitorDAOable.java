package com.controller.DAO;

import com.model.Visitor;

public interface VisitorDAOable {

    public Visitor getRegisteredVisitor(String email) throws Exception;

    public void addRegistredVisitor(Visitor visitor) throws Exception;


}
