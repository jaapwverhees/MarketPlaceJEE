package com.view;

import com.controller.ProductController;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.view.util.ControllerService.getProductController;
import static com.view.util.UserInputReader.*;

public class CreateArticle {

    private final Visitor visitor;
    private final Set<DeliveryOption> deliveryOptions = new HashSet<>();
    private final Set<Category> newProductCategories = new HashSet<>();
    private final ProductController controller = getProductController();
    private final List<Category> possibleCategoryList = controller.getAllCategories();


    public CreateArticle(Visitor visitor) {
        this.visitor = visitor;
    }

    public void start() {

        String name = inputString("Voer de naam van het product in:");

        String description = inputString("voer de beschrijving in");

        displayDeliveryOptions();

        addDeliveryOptions();

        displayPossibleCategoryOptions();

        addCategoryOption();

        BigDecimal price = inputBigDecimal("Voer de prijs in");

        System.out.println(controller.addArticle(name, description, visitor, deliveryOptions, newProductCategories, price));

        new LoggedInMenu(visitor).start();
    }

    private void addCategoryOption() {

        int choice = inputInt("Voer uw gewenste Categorie in") - 1;
        if (choice < 0 || choice > possibleCategoryList.size()) {
            System.out.println("ongeldige invoer, probeer het nogmaals");
            addCategoryOption();
        } else {
            newProductCategories.add(possibleCategoryList.get(choice));
        }

        if (choose("wilt u nog een mogelijkheid toevoegen?")) {
            addCategoryOption();
        }
    }

    private void displayPossibleCategoryOptions() {
        System.out.println("De  categorieen zijn:");
        for (int i = 0; i < possibleCategoryList.size(); i++) {
            System.out.printf("%s:  %s\n", i + 1, possibleCategoryList.get(i));
        }
        System.out.println();
    }

    private void addDeliveryOptions() {
        boolean checker = true;
        String choice = inputString("Voer uw gewenste levermogelijkheid in");
        for (DeliveryOption deliveryOption : visitor.getDeliveryOptions()) {
            if (choice.equalsIgnoreCase(deliveryOption.name())) {
                deliveryOptions.add(deliveryOption);
                checker = false;
            }
        }
        if (checker) {
            System.out.println("ongeldige invoer, probeer het nogmaals");
            addDeliveryOptions();
        } else if (choose("wilt u nog een mogelijkheid toevoegen?")) {
            addDeliveryOptions();
        }
    }

    private boolean choose(String message) {

        String choice = inputString(message).toLowerCase();

        switch (choice) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("ongeldige invoer, y of n");
                return choose(message);
        }
    }

    private void displayDeliveryOptions() {
        System.out.println("De leveringsmogelijkheden zijn:");
        for (DeliveryOption deliveryOption : visitor.getDeliveryOptions()) {
            System.out.println(deliveryOption.toString());
        }
    }
}
