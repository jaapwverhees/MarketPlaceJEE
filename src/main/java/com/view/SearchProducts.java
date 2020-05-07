package com.view;

import com.controller.ProductController;
import com.model.product.Category;
import com.model.product.Product;
import com.util.exeptions.CustomException;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class SearchProducts {

    private final Scanner scanner = new Scanner(System.in);

    private final ProductController controller = new ProductController();

    public void start() {
        System.out.println("waarop wilt u zoeken?\n" +
                "1:     categorie\n" +
                "2:     prijs\n" +
                "3:     naam\n" +
                "4:     laat alle artikelen zien\n" +
                "5:     exit");
        searchOptions(String.valueOf(scanner.nextLine()));
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
                System.out.println("verkeerde invoer, selecteer opnieuw uw keuze...");
                start();
        }
    }

    private void showAllProducts() {
        List<Product> products = controller.getAllProducts();
        displayProducts(products);
    }

    private void byName() {
        System.out.println("voer de naam van het product in");
        String productname = scanner.nextLine();
        List<Product> products = controller.getProductsByName(productname);
        displayProducts(products);
    }

    private void price() {
        BigDecimal minimum = BigDecimal.ONE;
        BigDecimal maximum = BigDecimal.ONE;
        try {
            minimum = bigDecimal("voer het minimum bedrag in");
            maximum = bigDecimal("Voer het maximum bedrag in");
            List<Product> products = controller.getProductsByPrice(minimum, maximum);
            displayProducts(products);
        } catch (NumberFormatException e) {
            System.out.println("geen geldig nummer, decimalen moeten gescheiden worden door een punt.");
            price();
        }
    }

    private void category() {
        System.out.println("De categorien zijn:");
        List<Category> categoryList = controller.getAllCategories();
        for (int i = 0; i < categoryList.size(); i++) {
            System.out.printf("productnummer:   %s\ndescription:    %s\n\n", i + 1, categoryList.get(i).getDescription());
        }
        System.out.println("selecteer uw keuze op basis van het categorie nummer");
        int choice = validChoiceFromList(categoryList);
        List<Product> productList = controller.getProductsByCategory(categoryList.get(choice - 1).getDescription());
        displayProducts(productList);
    }

    private void displayProducts(List<Product> productList) {
        if (productList.isEmpty()) {
            System.out.println("geen overeenkomende resultaten");
        }
        for (Product product : productList) {
            System.out.printf("     ---product---\n" +
                            "name: %s\n" +
                            "price: %s\n" +
                            "deliveryOptions: %s\n" +
                            "----------\n",
                    product.getName(), product.getPrice().toString(), product.getDeliveryOptions().toString());
        }
    }

    private int validChoiceFromList(List list) {
        int choice = 0;
        try {
            choice = Integer.parseInt(scanner.nextLine());
            if (choice < 1 || choice > list.size() + 1) {
                throw new CustomException("geen geldig nummer");
            }
        } catch (NumberFormatException e) {
            System.out.println("geen nummer, probeer opnieuw");
            validChoiceFromList(list);
        } catch (CustomException e) {
            System.out.println(e.getMessage());
            validChoiceFromList(list);
        }
        return choice;
    }

    private BigDecimal bigDecimal(String string) {
        System.out.println(string);
        BigDecimal bigDecimal = new BigDecimal(Double.valueOf(scanner.nextLine()));
        return bigDecimal;
    }
}
