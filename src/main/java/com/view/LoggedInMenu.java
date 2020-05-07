package com.view;

import com.model.Visitor;

import static com.view.util.UserInputReader.inputString;

public class LoggedInMenu {

    private final Visitor visitor;

    public LoggedInMenu(Visitor visitor) {
        this.visitor = visitor;
    }

    public void start() {
        System.out.println("Welkom to BDMarketplace, uw opties:\n\n" +
                "1: voer artikel in\n" +
                "2: zoek product\n" +
                "3: log uit\n" +
                "4: sluit af\n\n");

        options(inputString("voer uw keuze in;"));
    }

    private void options(String input) {
        switch (input) {
            case "1":
                new CreateArticle(visitor).start();
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
