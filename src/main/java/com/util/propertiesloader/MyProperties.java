package com.util.propertiesloader;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class MyProperties {

    private static String fileName = "META-INF/props.properties";

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

    public static void setFileName(String newFileName){
        fileName = newFileName;
    }
}
