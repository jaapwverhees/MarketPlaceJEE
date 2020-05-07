package com.view;

import java.util.Scanner;

public class MainMenu {

    private Scanner scanner = new Scanner(System.in);


    public void start(){
        System.out.println("Welkom to BDMarketplace, uw opties:\n\n"+
                "1: login\n"+
                "2: create Account\n"+
                "3: exit\n\n"+
                "voer uw keuze in;");
        options(String.valueOf(scanner.nextLine()));
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