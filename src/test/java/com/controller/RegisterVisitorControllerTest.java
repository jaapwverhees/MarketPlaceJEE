package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.model.DeliveryOption;
import com.util.mail.MailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterVisitorControllerTest {

    private final RegisteredVisitorDAOable DOA = mock(RegisteredVisitorDAO.class);

    private final MailService mailService = mock(MailService.class);

    private final RegisterVisitorController controller = new RegisterVisitorController();

    private final Set<DeliveryOption> array = new HashSet<>();


    RegisterVisitorControllerTest() {
    }

    @BeforeEach
    void setUp() throws Exception {

        array.add(DeliveryOption.WAREHOUSE);

        controller.setRegisteredVisitorDAO(DOA);
        doNothing().when(DOA).addRegistredVisitor(any());

        controller.setMailService(mailService);
        doNothing().when(mailService).sendMail(any(), any(), any());
    }

    @Test
    void testSuccessfulRegistration() {

        Assertions.assertEquals("invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres", controller.registerVisitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));

        Assertions.assertEquals("invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres", controller.registerVisitor("jaap", "Test@Testnotreal.com", array, "street", 12, "0000AZ"));

        Assertions.assertEquals("invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres", controller.registerVisitor("jaap", "Test@Testnotreal.com", array));

    }

    @Test
    void testUnsuccessfulRegistrationAccountAlreadyExists() throws Exception {

        doThrow(new SQLIntegrityConstraintViolationException()).when(DOA).addRegistredVisitor(any());
        controller.setRegisteredVisitorDAO(DOA);

        Assertions.assertEquals("Kon account niet creëren, email adres al in gebruik.", controller.registerVisitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));

        Assertions.assertEquals("Kon account niet creëren, email adres al in gebruik.", controller.registerVisitor("jaap", "Test@Testnotreal.com", array, "street", 12, "0000AZ"));

        Assertions.assertEquals("Kon account niet creëren, email adres al in gebruik.", controller.registerVisitor("jaap", "Test@Testnotreal.com", array));

    }
}