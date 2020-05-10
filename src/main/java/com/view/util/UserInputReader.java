package com.view.util;

import java.math.BigDecimal;
import java.util.Scanner;

import static com.view.util.Print.print;

public class UserInputReader {

    private final static Scanner scanner = new Scanner(System.in);

    public static String inputString(String message) {
        print(message);
        return scanner.nextLine();
    }

    public static int inputInt(String message) {
        print(message);
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            print("geen geldig cijfer, probeer het nog eens");
            return inputInt(message);
        }
    }

    public static BigDecimal inputBigDecimal(String message) {
        print(message);
        try {
            return BigDecimal.valueOf(Double.parseDouble(scanner.nextLine()));
        } catch (NumberFormatException e) {
            print("geen geldig nummer, decimalen moeten gescheiden worden door een punt.");
            return inputBigDecimal(message);
        }
    }
}
