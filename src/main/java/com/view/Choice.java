package com.view;

import java.util.Scanner;

public class Choice {

    static boolean choose() {
        Scanner scanner = new Scanner(System.in);
        String choice = String.valueOf(scanner.nextLine()).toLowerCase();
        scanner.close();

        switch (choice) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("ongeldige invoer");
                choose();
        }
        return false;
    }
}
