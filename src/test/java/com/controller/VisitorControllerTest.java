package com.controller;

import com.controller.DAO.VisitorDAO;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.Product;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import javax.mail.MessagingException;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VisitorControllerTest {

    private final String userName = "userjaap";
    private final String streetName = "streetname";
    private final int streetNumber = 12;
    private final String suffix = "A";
    private final String zipcode = "5555AZ";
    @Mock
    VisitorDAO visitorDAO;
    @Mock
    Logger log;
    @Mock
    MailService mailService;
    @InjectMocks
    VisitorController controller = new VisitorController();
    private String email = "test@test.nl";
    private Set<DeliveryOption> deliveryOptions;

    @BeforeEach
    void setUp() {
        deliveryOptions = new HashSet<>();
        deliveryOptions.add(DeliveryOption.WAREHOUSE);
    }

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
    void registerinValidVisitor() {
        email = "invalidEmailAddress";
        Assertions.assertEquals("String email is invalid", controller.registerVisitor(userName, email, deliveryOptions));
    }

    @Test
    void registerVisitorDataBaseError() throws Exception {
        doThrow(new RuntimeException("test")).when(visitorDAO).createVisitor(any());

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test",controller.registerVisitor(userName, email, deliveryOptions));
    }
    @Test
    void registerVisitorThatAlreadyExists() throws Exception {
        doThrow(new CustomException("test")).when(visitorDAO).createVisitor(any());

        assertEquals("test",controller.registerVisitor(userName, email, deliveryOptions));
    }
    @Test
    void registerVisitorCantSendPasswordEmail() throws Exception {
        doThrow(new MessagingException("test")).when(visitorDAO).createVisitor(any());
        assertEquals("Er heeft een fout plaats gevonden, en uw wachtwoord kan niet worden verzonden. Er word contact met u opgenomen",controller.registerVisitor(userName, email, deliveryOptions));
    }

    @Test
    void registerVisitorUnexpectedException() throws Exception {
        doThrow(new RuntimeException("test")).when(visitorDAO).createVisitor(any());

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test",controller.registerVisitor(userName, email, deliveryOptions));
    }
    @Test
    void registerVisitorWithAddressUnexpectedException() throws Exception {
        doThrow(new RuntimeException("test")).when(visitorDAO).createVisitor(any());

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test",controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode));
    }
    @Test
    void registerVisitorWithAddressWithoutSuffixUnexpectedException() throws Exception {
        doThrow(new RuntimeException("test")).when(visitorDAO).createVisitor(any());

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test",controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode));
    }

}