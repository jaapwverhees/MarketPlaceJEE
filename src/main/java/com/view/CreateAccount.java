package com.view;

import com.controller.VisitorController;
import com.model.DeliveryOption;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CreateAccount {

    private static final Scanner scanner = new Scanner(System.in);
    VisitorController controller = new VisitorController();
    private String userName;
    private String email;
    private Set<DeliveryOption> deliveryOptions = new HashSet<>();
    private String streetName;
    private int streetNumber;
    private String suffix;
    private String zipcode;

    public void createAccount() {

        voorwaarden();

        createUser();
        //TODO technically "Front end" shouldnt know about Deliveryoptions Enum.
        addDeliveryOptions();

        if (this.deliveryOptions.contains(DeliveryOption.PICKUPFROMHOME)) {
            createAdress();
        } else {
            System.out.println("Wilt u uw adres toevoegen? (y/n)");
            if (choose()) {
                createAdress();
            }
        }

        if (streetName == null) {
            System.out.println(controller.registerVisitor(userName, email, deliveryOptions));
        } else if (suffix == null) {
            System.out.println(controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode));
        } else {
            System.out.println(controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode));
        }

    }

    private void voorwaarden() {
        System.out.println("Indien u gebruik wil maken van de DB Marketplace moet u akkoort gaan met de volgende voorwaarden;\n\n" +
                FileReader.read("files/provisions") +
                "\ngaat u akkoort? (y/n)"
        );
        if (!choose()) {
            //TODO when created you would return to a main menu.
            System.out.println("aan het afsluiten....");
            System.exit(0);
        }

    }

    private void createUser() {
        System.out.println("Voer uw username in");
        this.userName = String.valueOf(scanner.nextLine());

        System.out.println("Voer uw emailadres in");
        this.email = String.valueOf(scanner.nextLine());
    }

    private void createAdress() {
        System.out.println("Voer uw straatnaam in");
        this.streetName = String.valueOf(scanner.nextLine());

        System.out.println("Voer uw huisnummer in");
        this.streetNumber = Integer.parseInt(scanner.nextLine());

        System.out.println("Voer uw huisnummer-suffix in. heeft u die niet, druk dan op enter");
        this.suffix = String.valueOf(scanner.nextLine());
        if (suffix.equals("")) {
            this.suffix = null;
        }
        System.out.println("Voer uw postcode in");
        this.zipcode = String.valueOf(scanner.nextLine());
    }

    private void addDeliveryOptions() {
        this.deliveryOptions = new HashSet<>();
        System.out.println("bezorgopties zijn:" +
                "1.    PICKUPFROMHOME,\n" +
                "2.    WAREHOUSE,\n" +
                "3.    DELIVERY,\n" +
                "4.    PAYONDELIVERY");

        System.out.println("voeg een bezorgoptie toe");
        String option = String.valueOf(scanner.nextLine());
        switch (option) {
            case "1":
                deliveryOptions.add(DeliveryOption.PICKUPFROMHOME);
                break;
            case "2":
                deliveryOptions.add(DeliveryOption.WAREHOUSE);
                break;
            case "3":
                deliveryOptions.add(DeliveryOption.DELIVERY);
                break;
            case "4":
                deliveryOptions.add(DeliveryOption.PAYONDELIVERY);
                break;
            default:
                System.out.println("invalid input, try again");
        }
        if (!deliveryOptions.isEmpty()) {
            System.out.println("your delivery options are:\n" + deliveryOptions.toString() +
                    "Wilt u nog een optie toevoegen?(y/n)");
        }
        if (choose()) {
            addDeliveryOptions();
        }
    }

    boolean choose() {
        String choice = String.valueOf(scanner.nextLine()).toLowerCase();

        switch (choice) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                System.out.println("ongeldige invoer");
                choose();
        }
        return false;
    }
}
