package com.controller.DAO;

import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import com.util.password.PasswordAuthentication;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

import static com.util.propertiesloader.MyProperties.get;

public class RegisteredVisitorDAO implements RegisteredVisitorDAOable {

    private final PasswordAuthentication aut = new PasswordAuthentication();
    
    private Connection conn;

    @Override
    public RegistredVisitor getRegisteredVisitor(String email) throws Exception {

        Set<DeliveryOption> deliveryOptions = getDeliveryOptions(email);

        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "SELECT * FROM registred_visitor WHERE email = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString(1, email);
        ResultSet resultSet = preparedStmt.executeQuery();

        RegistredVisitor visitor;

        if (resultSet.next()) {
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            if (resultSet.getString("streetname") == null) {
                visitor = new RegistredVisitor(username, email, deliveryOptions);
            } else {
                String streetname = resultSet.getString("streetname");
                int streetnumber = resultSet.getInt("streetnumber");
                String suffix = resultSet.getString("suffix");
                String zipcode = resultSet.getString("zipcode");
                visitor = new RegistredVisitor(username, email, deliveryOptions, streetname, streetnumber, suffix, zipcode);

            }
            visitor.setPassword(password);
            preparedStmt.close();
            conn.close();
            return visitor;
        }
        return null;
    }

    @Override
    public void addRegistredVisitor(RegistredVisitor visitor) throws Exception {

        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "INSERT INTO registred_visitor (email, username, streetname, streetnumber,suffix, zipcode, password)VALUES (?,?,?,?,?,?,?);";

        //TODO how to clean up?
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, visitor.getEmail());
        preparedStmt.setString(2, visitor.getUserName());
        if (visitor.getAddress() != null && visitor.getAddress().getSuffix() != null) {
            preparedStmt.setString(3, visitor.getAddress().getStreetName());
            preparedStmt.setInt(4, visitor.getAddress().getStreetNumber());
            preparedStmt.setString(5, visitor.getAddress().getSuffix());
            preparedStmt.setString(6, visitor.getAddress().getZipcode());
        } else if (visitor.getAddress() != null) {
            preparedStmt.setString(3, visitor.getAddress().getStreetName());
            preparedStmt.setInt(4, visitor.getAddress().getStreetNumber());
            preparedStmt.setNull(5, Types.VARCHAR);
            preparedStmt.setString(6, visitor.getAddress().getZipcode());
        } else {
            preparedStmt.setNull(3, Types.VARCHAR);
            preparedStmt.setNull(4, Types.INTEGER);
            preparedStmt.setNull(5, Types.VARCHAR);
            preparedStmt.setNull(6, Types.VARCHAR);
        }
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

    private Set<DeliveryOption> getDeliveryOptions(String email) throws SQLException {

        conn = DriverManager.getConnection(get("database.url"), get("database.user"), get("database.password"));

        String query = "SELECT * FROM delivery_options WHERE visitor_id = ?";

        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setString(1, email);
        ResultSet resultSet = preparedStmt.executeQuery();
        Set<DeliveryOption> deliveryOptions = new HashSet<>();

        while (resultSet.next()) {
            deliveryOptions.add(DeliveryOption.valueOf(resultSet.getString("delivery_option")));
        }
        preparedStmt.close();
        conn.close();

        return deliveryOptions;
    }
}
