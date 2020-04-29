package com.controller;

import com.controller.DAO.VisitorDAO;
import com.model.DeliveryOption;
import com.util.mail.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;
import org.slf4j.Logger;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitorControllerTest {

    @Mock
    VisitorDAO visitorDAO;
    @Mock
    Logger log;
    @Mock
    MailService mailService;
    @InjectMocks
    VisitorController controller = new VisitorController();
    private String userName = "userjaap";
    private String email = "test@test.nl";
    private Set<DeliveryOption> deliveryOptions;
    private String streetName = "streetname";
    private int streetNumber = 12;
    private String suffix = "A";
    private String zipcode = "5555AZ";

    @BeforeEach
    void setUp() throws Exception {
        deliveryOptions = new HashSet<>();
        deliveryOptions.add(DeliveryOption.WAREHOUSE);
    }

    //tests fail, don't know why
    @Test
    void registerValidVisitorWithAddress() throws Exception {
        controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
        verify(visitorDAO, atLeastOnce()).createVisitor(any());
        verify(mailService, atLeastOnce()).sendMail(any(), any(), any());

    }

    @Test
    void registerValidVisitorWithAddresswithoutSuffix() throws Exception {
        controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
        verify(visitorDAO, atLeastOnce()).createVisitor(any());
        verify(mailService, atLeastOnce()).sendMail(any(), any(), any());
    }

    @Test
    void registerValidVisitorWithoutAddress() throws Exception {
        controller.registerVisitor(userName, email, deliveryOptions);
        verify(visitorDAO, atLeastOnce()).createVisitor(any());
        verify(mailService, atLeastOnce()).sendMail(any(), any(), any());
    }

    @Test
    void registerinValidVisitor() throws Exception {
        email = "blabal";
        Assertions.assertEquals("String email is invalid", controller.registerVisitor(userName, email, deliveryOptions));
    }
}