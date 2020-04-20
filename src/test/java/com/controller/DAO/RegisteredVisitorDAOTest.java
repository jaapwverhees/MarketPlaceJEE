package com.controller.DAO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RegisteredVisitorDAOTest {

    H2Database database = new H2Database();
    RegisteredVisitorDAO DAO = new RegisteredVisitorDAO();


    @BeforeEach
    void setUp() {
        //TODO load different properties for testcases from resource folder.

        database.setUp();
        DAO.setDatabaseURL("jdbc:h2:~/test");
        DAO.setDatabasePassword("");
        DAO.setDatabaseUser("sa");
    }

    @Test
    void getRegisteredVisitor() throws Exception {
        assertEquals("test", DAO.getRegisteredVisitor("test@test.nl").getUserName());
    }

    @Test
    void addRegistredVisitor() {
    }
}