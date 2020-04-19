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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegistredvisitorLoginControllerTest {

    private RegistredvisitorLoginController controller = new RegistredvisitorLoginController();

    private final String password = "ThisIsAnPassword";

    //private RegistredVisitor visitor;

    private final PasswordAuthentication aut = new PasswordAuthentication();

    private final RegisteredVisitorDAO DAO = mock(RegisteredVisitorDAO.class);

    @BeforeEach
    void setup() throws Exception {

        controller.setRegisteredVisitorDAO(DAO);
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        RegistredVisitor visitor = new RegistredVisitor("test", "test@test.nl", array);
        visitor.setPassword(aut.hash(password));
        when(DAO.getRegisteredVisitor(any())).thenReturn(visitor);
    }

    @Test
    void validloginReturnsRegistredVisitor(){

        assertEquals("test", controller.login("test@test.nl", password).getUserName());
    }

    @Test
    void loginInvalidPasswordWillReturnNull() {

        assertNull(controller.login("test@test.nl", "invalidPassword"));
    }
}