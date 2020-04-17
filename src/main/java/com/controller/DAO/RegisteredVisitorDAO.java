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

    private Connection conn;

    private final PasswordAuthentication aut = new PasswordAuthentication();

    @Override
    public RegistredVisitor getRegisteredVisitor(String email) throws Exception {
        return null;
    }

    @Override
    public void addRegistredVisitor(RegistredVisitor visitor) throws Exception {

        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "INSERT INTO registred_visitor (email, username, streetname, streetnumber,suffix, zipcode, password)VALUES (?,?,?,?,?,?,?);";

        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, visitor.getEmail());
        preparedStmt.setString(2, visitor.getUserName());
        preparedStmt.setString(3, visitor.getAddress().getStreetName());
        preparedStmt.setInt(4, visitor.getAddress().getStreetNumber());
        preparedStmt.setString(5, visitor.getAddress().getSuffix());
        preparedStmt.setString(6, visitor.getAddress().getZipcode());
        preparedStmt.setString(7, aut.hash(visitor.getPassword()));

        preparedStmt.execute();

        conn.close();

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
