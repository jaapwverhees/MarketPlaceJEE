package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.logging.ErrorLogger;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;

import javax.mail.MessagingException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Set;

public class RegisterVisitorController {

    private static final String succesResponse = "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";

    private RegisteredVisitorDAOable registeredVisitorDAO = new RegisteredVisitorDAO();

    private MailService mailService = new MailService();

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return succesResponse;
    }

    private String exceptionHandler(Exception e) {

        if (e instanceof CustomException) {
            return e.getMessage();
        } else if (e instanceof SQLIntegrityConstraintViolationException) {
            return "Kon account niet creëren, email adres al in gebruik.";
        } else if (e instanceof MessagingException) {
            ErrorLogger.create(e);
            return "Er heeft een fout plaats gevonden, en uw wachtwoord kan niet worden verzonden. Er word contact met u opgenomen";
        } else {
            ErrorLogger.create(e);
            return "Er heeft een overwachte fout plaatsgevonden" + e.getMessage();
        }
    }

    private void sendConfirmationEmail(RegistredVisitor visitor) throws MessagingException {

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

    //TODO how to test without setter
    public void setRegisteredVisitorDAO(RegisteredVisitorDAOable registeredVisitorDAO) {
        this.registeredVisitorDAO = registeredVisitorDAO;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }
}
