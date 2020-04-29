package com.controller.DAO;

import com.model.Visitor;

public interface VisitorDAOable {

    public Visitor getVisitor(String email) throws Exception;

    public void createVisitor(Visitor visitor) throws Exception;


}
