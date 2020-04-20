package com.util.propertiesloader;

import com.util.logging.ErrorLogger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

    private static final String fileName = "database.properties";

    static Properties prop = new Properties();

    static {
        try (InputStream input = com.util.propertiesloader.MyProperties.class.getClassLoader().getResourceAsStream("database.properties")) {
            prop.load(input);
        } catch (IOException ex) {
            ErrorLogger.create(ex);
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
