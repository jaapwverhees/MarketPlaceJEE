package com.view;

import com.controller.ProductController;
import com.model.product.Category;
import com.model.product.Product;
import com.view.util.Print;

import java.math.BigDecimal;
import java.util.List;

import static com.view.util.ControllerService.getProductController;
import static com.view.util.Print.print;
import static com.view.util.UserInputReader.*;
import static com.view.util.Validator.validChoiceFromList;

public class SearchProducts {

    private final ProductController controller = getProductController();

    public void start() {
        print(displayOptions());

        searchOptions(inputString("Voer het gewenste nummer in"));
    }

    public String displayOptions() {
        return "Zoekopties:\n" +
                "1:     categorie\n" +
                "2:     prijs\n" +
                "3:     naam\n" +
                "4:     laat alle artikelen zien\n" +
                "5:     exit";
    }

    private void searchOptions(String input) {
        switch (input) {
            case "1":
                category();
                break;
            case "2":
                price();
                break;
            case "3":
                byName();
                break;
            case "4":
                showAllProducts();
                break;
            case "5":
                System.exit(0);
            default:
                print("verkeerde invoer, selecteer opnieuw uw keuze...");
                start();
        }
    }

    private void showAllProducts() {
        displayProducts(controller.getAllProducts());
    }

    private void byName() {
        String productName = inputString("voer de naam van het product in");
        displayProducts(controller.getProductsByName(productName));
    }

    private void price() {

        BigDecimal minimum = inputBigDecimal("voer het minimum bedrag in");
        BigDecimal maximum = inputBigDecimal("Voer het maximum bedrag in");
        List<Product> products = controller.getProductsByPrice(minimum, maximum);
        if (products.isEmpty()) {
            print("Geen producten gevonden binnen deze waarden");
        } else {
            displayProducts(products);
        }
    }

    private void category() {
        List<Category> categoryList = controller.getAllCategories();
        displayCategories(categoryList);
        int choice = inputInt("selecteer uw keuze op basis van het categorie nummer");
        if (validChoiceFromList(categoryList, choice)) {
            List<Product> productList = controller.getProductsByCategory(categoryList.get(choice - 1).getDescription());
            displayProducts(productList);
        } else {
            print("ongeldige keuze, probeer het opnieuw");
            category();
        }
    }

    private void displayCategories(List<Category> categoryList) {
        print("De categorien zijn:");
        for (int i = 0; i < categoryList.size(); i++) {
            print("productnummer:   %s\ndescription:    %s\n\n", i + 1, categoryList.get(i).getDescription());
        }
    }

    private void displayProducts(List<Product> productList) {
        if (productList.isEmpty()) {
            print("geen overeenkomende resultaten");
        } else {
            for (Product product : productList) {
                print("     ---product---\n" +
                                "name: %s\n" +
                                "price: %s\n" +
                                "deliveryOptions: %s\n" +
                                "----------\n",
                        product.getName(), product.getPrice().toString(), product.getDeliveryOptions().toString());
            }
        }
    }
}
