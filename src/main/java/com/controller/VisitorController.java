package com.controller;

import com.controller.DAO.VisitorDAO;
import com.controller.DAO.VisitorDAOable;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.MessagingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

public class VisitorController {

    private static final String succesResponse = "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";

    //@Inject
    private Logger errorLogger = LoggerFactory.getLogger(this.getClass());

    //@Inject
    private VisitorDAOable visitorDAO = new VisitorDAO();

    //@Inject
    private MailService mailService = new MailService();

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {

        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) {

        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions) {

        try {
            Visitor visitor = new Visitor(userName, email, deliveryOptions);
            visitorDAO.createVisitor(visitor);
            sendConfirmationEmail(visitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    private void sendConfirmationEmail(Visitor visitor) throws MessagingException {

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
                        visitor.getPassword()));
    }

    private String exceptionHandler(Exception e) {

        if (e instanceof CustomException) {
            return e.getMessage();
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            return "Kon account niet creëren, email adres al in gebruik.";
        } else if (e instanceof MessagingException) {
            errorLogger.error("Error", e);
            return "Er heeft een fout plaats gevonden, en uw wachtwoord kan niet worden verzonden. Er word contact met u opgenomen";
        } else {
            errorLogger.error("Error", e);
            return "Er heeft een overwachte fout plaatsgevonden" + e.getMessage();
        }
    }
}
