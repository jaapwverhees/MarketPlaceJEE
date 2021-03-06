package com.controller;

import com.controller.DAO.VisitorDAO;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.util.Set;

public class VisitorController {

    private static final String succesResponse = "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";

    private final Logger errorLogger = LoggerFactory.getLogger(this.getClass());

    //Mocked by mockito during tests, cannot be final.
    @SuppressWarnings("FieldMayBeFinal")
    private VisitorDAO visitorDAO = new VisitorDAO();

    //Mocked by mockito during tests, cannot be final.
    @SuppressWarnings("FieldMayBeFinal")
    private MailService mailService = new MailService();

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {

        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            String password = visitor.getPassword();
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor, password);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) {

        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
            String password = visitor.getPassword();
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor, password);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions) {
        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions);
            String password = visitor.getPassword();
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor, password);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    private void sendConfirmationEmail(Visitor visitor, String password) throws MessagingException {

        mailService.sendMail(
                visitor.getEmail(),
                "succesvol geregistreerd by BDmarketplace",
                String.format("Beste %s,\n" +
                                "Uw Account is geregistreerd.\n" +
                                "uw wachtwoord is : %s" +
                                "\n" +
                                "Met vriendelijke groet," +
                                "klantenteam BDmarketPlace",
                        visitor.getUserName(),
                        password));
    }

    private String exceptionHandler(Exception e) {
        if (e instanceof CustomException) {
            return e.getMessage();
        } else if (e instanceof MessagingException) {
            errorLogger.error("Error: ", e);
            return "Er heeft een fout plaats gevonden, en uw wachtwoord kan niet worden verzonden. Er word contact met u opgenomen";
        } else {
            errorLogger.error("Error", e);
            return "Er heeft een overwachte fout plaatsgevonden: " + e.getMessage();
        }
    }
}
