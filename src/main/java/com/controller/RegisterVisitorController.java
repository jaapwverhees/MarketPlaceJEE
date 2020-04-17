package com.controller;

import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.exeptions.CustomException;
import com.util.mail.MailService;
import com.util.password.PasswordAuthentication;

import javax.mail.MessagingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.util.propertiesloader.MyProperties.get;


//TODO implement RVDAO
public class RegisterVisitorController {
    //TODO could return Json(like) for the hell of it.
    public String registerVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) {
        RegistredVisitor visitor;
        try {
            visitor = createRegisteredVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
            insertNewRegistredVisitor(visitor);
            insertNewDeliveryOptions(visitor);
            sendConfirmationEmail(visitor);
        } catch (Exception e) {
            if (e instanceof CustomException) {
                return e.getMessage();
            }//TODO create errorlog
            return "Er heeft een overwachte fout plaatsgevonden";
        }
        return "invoer succesvol! Er is een email verstuurd naar uw opgegeven emailadres";
    }

    private RegistredVisitor createRegisteredVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) throws Exception {
        if (streetName.equals("") && streetNumber == 0 && zipcode.equals("")) {
            return new RegistredVisitor(userName, email, deliveryOptions);
        } else if (suffix.equals("")) {
            return new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
        }
        return new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
    }

    //TODO should this method be in another class? when underlying database is changed, this class needs to be changed to
    // and this is not relevant for all functions.
    //TODO put in check so that empty fields remain null?
    private void insertNewRegistredVisitor(RegistredVisitor visitor) throws SQLException {
        PasswordAuthentication aut = new PasswordAuthentication();
        Connection conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "INSERT INTO registred_visitor (email, username, streetname, streetnumber,suffix, zipcode, password)VALUES (?,?,?,?,?,?,?);";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, visitor.getEmail());
        preparedStmt.setString(2, visitor.getUserName());
        preparedStmt.setString(3, visitor.getStreetName());
        preparedStmt.setInt(4, visitor.getStreetNumber());
        preparedStmt.setString(5, visitor.getSuffix());
        preparedStmt.setString(6, visitor.getZipcode());
        preparedStmt.setString(7, aut.hash(visitor.getPassword()));

        preparedStmt.execute();

        conn.close();
    }

    //TODO another class?

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
