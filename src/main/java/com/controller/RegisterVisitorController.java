package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.ErrorLogger;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.Set;


public class RegisterVisitorController {

    private RegisteredVisitorDAOable registeredVisitorDAO = new RegisteredVisitorDAO();

    //TODO how to test without setter
    public void setRegisteredVisitorDAO(RegisteredVisitorDAOable registeredVisitorDAO) {
        this.registeredVisitorDAO = registeredVisitorDAO;
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                return e.getMessage();
            } else {
                ErrorLogger.create(e);
                return "Er heeft een overwachte fout plaatsgevonden";
            }
        }
        return "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                return e.getMessage();
            } else {
                ErrorLogger.create(e);
                return "Er heeft een overwachte fout plaatsgevonden";
            }
        }
        return "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";
    }

    public String registerVisitor(String userName, String email, Set<DeliveryOption> deliveryOptions) {

        try {
            RegistredVisitor registredVisitor = new RegistredVisitor(userName, email, deliveryOptions);
            registeredVisitorDAO.addRegistredVisitor(registredVisitor);
            sendConfirmationEmail(registredVisitor);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                return e.getMessage();
            } else if(e instanceof MessagingException){
                ErrorLogger.create(e);
                return "Er heeft een fout plaats gevonden, en uw wachtwoord kan niet worden verzonden. Er word contact met u opgenomen";
            }else {
                ErrorLogger.create(e);
                return "Er heeft een overwachte fout plaatsgevonden";
            }
        }
        return "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";
    }

    private void sendConfirmationEmail(RegistredVisitor visitor) throws MessagingException {
        MailService.sendMail(visitor.getEmail(), "succesvol geregistreerd by BDmarketplace",
                String.format("Beste %s,\n" +
                        "Uw Account is geregistreerd.\n" +
                        "uw wachtwoord is : %s" +
                        "\n" +
                        "Met vriendelijke groet," +
                        "klantenteam BDmarketPlace", visitor.getUserName(), visitor.getPassword()));
    }
}
