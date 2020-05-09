package com.controller.DAO;

import com.model.Visitor;

@Deprecated
public interface VisitorDAOable {

    public Visitor getVisitor(String email) throws Exception;

    public void createVisitor(Visitor visitor) throws Exception;


}
