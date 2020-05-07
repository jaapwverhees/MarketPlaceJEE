package com.view.util;

import java.math.BigDecimal;
import java.util.Scanner;

public class UserInputReader {

    private final static Scanner scanner = new Scanner(System.in);

    public static String inputString(String message) {
        System.out.println(message);
        return scanner.nextLine();
    }
    public static int inputInt(String message){
        System.out.println(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e){
            System.out.println("geen geldig cijfer, probeer het nogmaals");
            return inputInt(message);
        }
    }

    public static BigDecimal inputBigDecimal(String message) {
        System.out.println(message);
        try {
            return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        }catch (NumberFormatException e) {
            System.out.println("geen geldig nummer, decimalen moeten gescheiden worden door een punt.");
            return inputBigDecimal(message);
        }
    }
}
