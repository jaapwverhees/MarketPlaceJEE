package com.view;

import com.controller.VisitorController;
import com.model.DeliveryOption;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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

        new MainMenu().start();
    }

    private void voorwaarden() {
        System.out.println("Indien u gebruik wil maken van de DB Marketplace moet u akkoort gaan met de volgende voorwaarden;\n\n" +
                FileReader.read("files/provisions") +
                "\ngaat u akkoort? (y/n)"
        );
        if (!choose()) {
            System.out.println("aan het afsluiten....");
            new MainMenu().start();
        }

    }

    private void createUser() {
        System.out.println("Voer uw username in");
        this.userName = String.valueOf(scanner.nextLine());

        System.out.println("Voer uw emailadres in");
        this.email = String.valueOf(scanner.nextLine());
        try{
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        }catch(AddressException e){
            this.email = null;
            System.out.println("niet geldig emailadres, probeer opnieuw");
            createUser();
        }
    }

    private void createAdress() {
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("ongeldig huisnummer, probeer opnieuw");
            createAdress();
        }
    }

    private void addDeliveryOptions() {
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
                addDeliveryOptions();
        }
        if (!deliveryOptions.isEmpty()) {
            System.out.println("your delivery options are:\n" + deliveryOptions.toString() +
                    "\nWilt u nog een optie toevoegen?(y/n)");
            if (choose()) {
                addDeliveryOptions();
            }
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
