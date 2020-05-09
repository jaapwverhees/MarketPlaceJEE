package com.view;

import com.controller.ProductController;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.PriceType;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.model.product.PriceType.TotalPrice;
import static com.model.product.PriceType.hourlyRate;
import static com.view.util.ControllerService.getProductController;
import static com.view.util.UserInputReader.*;
import static com.view.util.Validator.chooseYOrN;

public class CreateArticle {

    private final Visitor visitor;
    private final Set<DeliveryOption> deliveryOptions = new HashSet<>();
    private final ProductController controller = getProductController();
    private final List<Category> possibleCategoryList = controller.getAllCategories();
    private Set<Category> productCategories = new HashSet<>();
    private String name;
    private String description;
    private BigDecimal price;

    public CreateArticle(Visitor visitor) {
        this.visitor = visitor;
    }

    //TODO StringBuilder for display methods
    public void start() {

        this.name = inputString("Voer de naam van het product in:");

        this.description = inputString("voer de beschrijving in");

        displayDeliveryOptions();

        addDeliveryOptions();

        displayPossibleCategoryOptions();

        productCategories = addCategoryOption(productCategories);

        this.price = inputBigDecimal("Voer de prijs in");

        createServiceOrProduct();

        new LoggedInMenu(visitor).start();
    }

    private void createServiceOrProduct() {
        if (chooseYOrN("is het product een dienst?")) {
            PriceType priceType = setPriceType();
            System.out.println(controller.addService(name, description, visitor, deliveryOptions, productCategories, price, priceType));
        } else {
            System.out.println(controller.addArticle(name, description, visitor, deliveryOptions, productCategories, price));
        }
    }

    private PriceType setPriceType() {
        String choice = inputString("kies welke betaalmogelijkheid is wilt, hourlyrate of totalprice");
        if (hourlyRate.name().equalsIgnoreCase(choice)) {
            return hourlyRate;
        } else if (TotalPrice.name().equalsIgnoreCase(choice)) {
            return TotalPrice;
        } else {
            System.out.println("ongeldige invoer, probeer opnieuw");
            return setPriceType();
        }
    }

    private Set<Category> addCategoryOption(Set<Category> categories) {

        int choice = inputInt("Voer uw gewenste Categorie in") - 1;
        if (choice < 0 || choice > possibleCategoryList.size()) {
            System.out.println("ongeldige invoer, probeer het nogmaals");
            return addCategoryOption(categories);
        } else {
            categories.add(possibleCategoryList.get(choice));
        }
        if (chooseYOrN("wilt u nog een mogelijkheid toevoegen?")) {
            return addCategoryOption(categories);
        }
        return categories;
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
        } else if (chooseYOrN("Wilt u nog een mogelijkheid toevoegen?")) {
            addDeliveryOptions();
        }
    }

    private void displayDeliveryOptions() {
        System.out.println("De leveringsmogelijkheden zijn:");
        for (DeliveryOption deliveryOption : visitor.getDeliveryOptions()) {
            System.out.println(deliveryOption.toString());
        }
    }
}
