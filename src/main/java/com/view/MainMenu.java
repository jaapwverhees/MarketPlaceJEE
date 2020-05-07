package com.view;

import com.view.util.UserInputReader;

import java.util.Scanner;

import static com.view.util.UserInputReader.inputString;

public class MainMenu {

    public void start(){
        options(inputString("Welkom to BDMarketplace, uw opties:\n\n"+
                "1: login\n"+
                "2: create Account\n"+
                "3: exit\n\n"+
                "voer uw keuze in;"));
    }

    private void options(String input){
        switch (input){
            case "1":
                new LoginMenu().login();
                break;
            case "2":
                new CreateAccount().createAccount();
                break;
            case "3":
                System.exit(0);
            default:
                System.out.println("Invalid input, returning to main menu...");
                start();
        }
    }
}
