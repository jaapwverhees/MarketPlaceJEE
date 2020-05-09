package com.view.util;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;

import static com.view.util.UserInputReader.inputString;

public class Validator {

    public static boolean validChoiceFromList(List list, int choice) {
        return choice >= 1 && choice <= list.size() + 1;
    }

    public static boolean chooseYOrN(String message) {
        switch (inputString(message).toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("ongeldige invoer");
                return chooseYOrN(message);
        }
    }
    public static boolean validEmailAddress(String email){
        try{
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        }catch(Exception e){
            return false;
        }
        return true;
    }
}
