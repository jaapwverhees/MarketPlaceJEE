package com.view;

import com.view.util.Print;

import static com.view.util.Print.print;
import static com.view.util.UserInputReader.inputString;

public class MainMenu {

    public void start() {
        print(displayOptions());

        options(inputString("Voer uw keuze in"));
    }

    private String displayOptions() {
        return "Welkom to BDMarketplace, uw opties:\n\n" +
                "1: login\n" +
                "2: create Account\n" +
                "3: exit\n";
    }

    private void options(String input) {
        switch (input) {
            case "1":
                new LoginMenu().login();
                break;
            case "2":
                new CreateAccount().createAccount();
                break;
            case "3":
                System.exit(0);
            default:
                print("Invalid input, returning to main menu...");
                start();
        }
    }
}
