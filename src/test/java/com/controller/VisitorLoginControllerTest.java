package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.password.PasswordAuthentication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class VisitorLoginControllerTest {

    VisitorLoginController controller = new VisitorLoginController();
    String password = "ThisIsAnPassword";
    RegistredVisitor visitor;
    private PasswordAuthentication aut = new PasswordAuthentication();
    private RegisteredVisitorDAO DAO = mock(RegisteredVisitorDAO.class);

    @BeforeEach
    void setup() throws Exception {
        controller.setRegisteredVisitorDAO(DAO);
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        visitor = new RegistredVisitor("test", "test@test.nl", array);
        visitor.setPassword(aut.hash(password));
        when(DAO.getRegisteredVisitor("test@test.nl")).thenReturn(visitor);
    }

    @Test
    void validloginReturnsRegistredVisitor() throws Exception {
        assertEquals("test", controller.login("test@test.nl", password).getUserName());
    }

    @Test
    void loginInvalidPasswordWillReturnNull() {
        assertNull(controller.login("test@test.nl", "invalidPassword"));
    }
}