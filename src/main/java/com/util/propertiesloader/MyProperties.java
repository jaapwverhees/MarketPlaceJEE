package com.util.propertiesloader;


import org.hibernate.internal.util.xml.ErrorLogger;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MyProperties {

    private static final String fileName = "META-INF/log4j.properties";

    static Properties prop = new Properties();

    static {
        try (InputStream input = com.util.propertiesloader.MyProperties.class.getClassLoader().getResourceAsStream(fileName)) {
            prop.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }
}
