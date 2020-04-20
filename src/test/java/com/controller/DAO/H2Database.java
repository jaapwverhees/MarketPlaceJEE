package com.controller.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class H2Database {

    public void setUp() {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DROP TABLE IF EXISTS delivery_options");
            stmt.executeUpdate("DROP TABLE IF EXISTS registred_visitor");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS registred_visitor (\n" +
                    "    email VARCHAR(255) primary KEY,\n" +
                    "    username VARCHAR(255),\n" +
                    "    streetname VARCHAR(255),\n" +
                    "    streetnumber INT,\n" +
                    "    suffix VARCHAR(255),\n" +
                    "    zipcode VARCHAR(6),\n" +
                    "    password VARCHAR(255)\n" +
                    ");");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS delivery_options(\n" +
                    "\tvisitor_id VARCHAR(255),\n" +
                    "    delivery_option VARCHAR(255),\n" +
                    "    PRIMARY KEY (visitor_id, delivery_option),\n" +
                    "    FOREIGN KEY (visitor_id) REFERENCES registred_visitor(email)\n" +
                    ");");
            stmt.executeUpdate("INSERT INTO registred_visitor ( email, username ) VALUES ( 'test@test.nl', 'test' )");
            stmt.executeUpdate("INSERT INTO delivery_options ( visitor_id, delivery_option ) VALUES ( 'test@test.nl', 'WAREHOUSE' )");
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}