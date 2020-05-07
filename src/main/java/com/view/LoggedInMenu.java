package com.view;

import com.model.Visitor;

import java.util.Scanner;

public class LoggedInMenu {

    private Scanner scanner = new Scanner(System.in);

    private Visitor visitor;

    public LoggedInMenu(Visitor visitor) {
        this.visitor = visitor;
    }

    public void start() {
        System.out.println("Welkom to BDMarketplace, uw opties:\n\n" +
                "1: voer artikel in\n" +
                "2: zoek product\n" +
                "3: log uit\n" +
                "4: sluit af\n\n" +
                "voer uw keuze in;");
        options(String.valueOf(scanner.nextLine()));
    }

    private void options(String input){
        LoginMenu loginMenu = new LoginMenu();
        CreateAccount createAccount = new CreateAccount();
        switch (input){
            case "1":
                new CreateArticle().start();
                break;
            case "2":
                new SearchProducts().start();
                break;
            case "3":
                new MainMenu().start();
            case "4":
                System.exit(0);
            default:
                System.out.println("verkeerde invoer, selecteer opnieuw uw keuze...");
                start();
        }
    }
}
