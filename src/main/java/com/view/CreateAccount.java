package com.view;

import com.controller.VisitorController;
import com.model.DeliveryOption;
import com.view.util.FileReader;
import com.view.util.Validator;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import static com.view.util.ControllerService.getVisitorController;
import static com.view.util.UserInputReader.inputString;
import static com.view.util.Validator.chooseYOrN;
import static com.view.util.Validator.validEmailAddress;

public class CreateAccount {

    private static final Scanner scanner = new Scanner(System.in);
    private final Set<DeliveryOption> deliveryOptions = new HashSet<>();
    VisitorController controller = getVisitorController();
    private String email;
    private String streetName;
    private int streetNumber;
    private String suffix;
    private String zipcode;

    public void createAccount() {

        voorwaarden();

        String userName = inputString("Voer uw gebruikernaam in:");

        String email = createEmail();

        addDeliveryOptions();

        if (this.deliveryOptions.contains(DeliveryOption.PICKUPFROMHOME)) {
            createAdress();
        } else {
            if (chooseYOrN("Wilt u uw adres toevoegen? (y/n)")) {
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
                FileReader.read("files/provisions"));
        if (!chooseYOrN("Gaat u akkoort? (y/n)")) {
            System.out.println("Terug naar het hooftmenu....");
            new MainMenu().start();
        }

    }

    private String createEmail() {
        String email = inputString("Voer uw emailadres in");

        if(!validEmailAddress(this.email)){
            return createEmail();
        }
        return email;
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
            System.out.println("your delivery options are:\n" + deliveryOptions.toString());
            if (chooseYOrN("Wilt u nog een optie toevoegen?(y/n)")) {
                addDeliveryOptions();
            }
        }
    }
}
