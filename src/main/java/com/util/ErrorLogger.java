package com.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.*;

public class ErrorLogger {

    private static final Logger LOGGER = Logger.getLogger(ErrorLogger.class.getName());

    public static void create(Exception exception) {

        try{

            Handler fileHandler  = new FileHandler("./errors.log", true);

            LOGGER.setUseParentHandlers(false);
            LOGGER.addHandler(fileHandler);


            fileHandler.setLevel(Level.WARNING);
            LOGGER.setLevel(Level.WARNING);

            LOGGER.log(Level.WARNING, String.format("%s\n%s", exception.getMessage(), Arrays.toString(exception.getStackTrace())));
        }catch(IOException logException){
            Handler consoleHandler = new ConsoleHandler();
            consoleHandler.setLevel(Level.SEVERE);
            LOGGER.addHandler(consoleHandler);
            LOGGER.log(Level.SEVERE, "Error occur in FileHandler.", exception);
        }
    }
}
