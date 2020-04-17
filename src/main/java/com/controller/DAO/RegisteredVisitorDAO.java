package com.controller.DAO;

import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.password.PasswordAuthentication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.util.propertiesloader.MyProperties.get;

public class RegisteredVisitorDAO implements RegisteredVisitorDAOable {

    private PreparedStatement preparedStmt;
    private Connection conn;
    private PasswordAuthentication aut;

    @Override
    public RegistredVisitor getRegisteredVisitor(String email) throws Exception {
        return null;
    }

    @Override
    public void addRegistredVisitor(RegistredVisitor visitor) throws Exception {
        aut = new PasswordAuthentication();
        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "INSERT INTO registred_visitor (email, username, streetname, streetnumber,suffix, zipcode, password)VALUES (?,?,?,?,?,?,?);";

        preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, visitor.getEmail());
        preparedStmt.setString(2, visitor.getUserName());
        preparedStmt.setString(3, visitor.getStreetName());
        preparedStmt.setInt(4, visitor.getStreetNumber());
        preparedStmt.setString(5, visitor.getSuffix());
        preparedStmt.setString(6, visitor.getZipcode());
        preparedStmt.setString(7, aut.hash(visitor.getPassword()));

        preparedStmt.execute();

        conn.close();

        //TODO what is wisdom. can i give the connection to the insertNewDeliveryOptions method?
        // i think that it is more neat to close the connection here and reopen it in the next method.
        insertNewDeliveryOptions(visitor);
    }

    private void insertNewDeliveryOptions(RegistredVisitor visitor) throws SQLException {

        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        for (DeliveryOption option : visitor.getDeliveryOptions()) {
            String query = "INSERT INTO delivery_options (visitor_id, delivery_option)VALUES (?,?);";

            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, visitor.getEmail());
            preparedStmt.setString(2, option.toString());
            preparedStmt.execute();

            preparedStmt.close();
        }
        conn.close();
    }
}
