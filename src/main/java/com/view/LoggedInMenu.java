package com.view;

import com.model.Visitor;
import com.view.util.Print;

import static com.view.util.Print.print;
import static com.view.util.UserInputReader.inputString;

public class LoggedInMenu {

    private final Visitor visitor;

    public LoggedInMenu(Visitor visitor) {
        this.visitor = visitor;
    }

    public void start() {

        print(displayOptions());

        options(inputString("voer uw keuze in;"));
    }

    private String displayOptions() {
        return "Welkom to BDMarketplace, uw opties:\n\n" +
                "1: voer artikel in\n" +
                "2: zoek product\n" +
                "3: log uit\n" +
                "4: sluit af\n\n";
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
                print("verkeerde invoer, selecteer opnieuw uw keuze...");
                start();
        }
    }
}
