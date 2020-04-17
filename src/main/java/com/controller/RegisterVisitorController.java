package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.controller.factory.RegisteredVisitorFactory;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;
import javax.mail.MessagingException;
import java.util.ArrayList;

public class RegisterVisitorController {

    private final RegisteredVisitorDAOable registeredVisitorDAO = new RegisteredVisitorDAO();


    //TODO return json (LP)
    //TODO in MVC i believe that the should have no knowledge of the models, so it would not be mvc to expect list of deliveryOptions.
    public String registerVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {

        try {
            RegistredVisitor visitor = new RegisteredVisitorFactory().create(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            registeredVisitorDAO.addRegistredVisitor(visitor);
            sendConfirmationEmail(visitor);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                return e.getMessage();
            }//TODO create errorlog
            return "Er heeft een overwachte fout plaatsgevonden";
        }
        return "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";
    }

    //TODO how to mock private methods
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
