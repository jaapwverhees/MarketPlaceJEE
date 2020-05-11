package com.view;

import com.controller.VisitorController;
import com.model.DeliveryOption;
import com.view.util.FileReader;
import com.view.util.UserInputReader;

import java.util.HashSet;
import java.util.Set;

import static com.view.util.ControllerService.getVisitorController;
import static com.view.util.UserInputReader.inputString;
import static com.view.util.Validator.chooseYOrN;
import static com.view.util.Validator.validEmailAddress;
import static com.view.util.Print.print;
//TODO make a call to backend to get deliveryoptions.

//TODO return voorwaarden from backend
public class CreateAccount {

    private final Set<DeliveryOption> deliveryOptions = new HashSet<>();
    VisitorController controller = getVisitorController();
    private String userName;
    private String email;
    private String streetName;
    private int streetNumber;
    private String suffix;
    private String zipcode;

    public void createAccount() {

        voorwaarden();

        userName = inputString("Voer uw gebruikernaam in:");

        email = createEmail();

        print(displayDeliverOptions());

        addDeliveryOptions();

        if (createAddressOrNot()) {
            createAddress();
        }

        print(registerAccount());

        new MainMenu().start();
    }

    private String displayDeliverOptions() {
        return "bezorgopties zijn:" +
                "1.    PICKUPFROMHOME,\n" +
                "2.    WAREHOUSE,\n" +
                "3.    DELIVERY,\n" +
                "4.    PAYONDELIVERY";
    }

    private String registerAccount() {
        if (streetName == null) {
            return controller.registerVisitor(userName, email, deliveryOptions);
        } else if (suffix == null) {
            return controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
        } else {
            return controller.registerVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
        }
    }

    private boolean createAddressOrNot() {
        if (this.deliveryOptions.contains(DeliveryOption.PICKUPFROMHOME)) {
            return true;
        } else {
            return chooseYOrN("Wilt u uw adres toevoegen? (y/n)");
        }
    }

    private void voorwaarden() {
        print("Indien u gebruik wil maken van de DB Marketplace moet u akkoort gaan met de volgende voorwaarden;\n\n" +
                FileReader.read("files/provisions"));
        if (!chooseYOrN("Gaat u akkoort? (y/n)")) {
            print("Terug naar het hooftmenu....");
            new MainMenu().start();
        }

    }

    private String createEmail() {
        String email = inputString("Voer uw emailadres in");

        if (!validEmailAddress(email)) {
            print("emailadres is niet geldig");
            return createEmail();
        }
        return email;
    }


    private void createAddress() {
        this.streetName = inputString("Voer uw straatnaam in");

        this.streetNumber = UserInputReader.inputInt("Voer uw huisnummer in");

        this.suffix = inputString("Voer uw huisnummer-suffix in. heeft u die niet, druk dan op enter");

        if (suffix.equals("")) {
            this.suffix = null;
        }

        this.zipcode = inputString("Voer uw postcode in");
    }

    private void addDeliveryOptions() {
        String option = inputString("voeg een bezorgoptie toe");
        switch (option) {
            case "1":
                this.deliveryOptions.add(DeliveryOption.PICKUPFROMHOME);
                break;
            case "2":
                this.deliveryOptions.add(DeliveryOption.WAREHOUSE);
                break;
            case "3":
                this.deliveryOptions.add(DeliveryOption.DELIVERY);
                break;
            case "4":
                deliveryOptions.add(DeliveryOption.PAYONDELIVERY);
                break;
            default:
                print("ongeldige invoer, probeer opnieuw");
                addDeliveryOptions();
        }
        if (!deliveryOptions.isEmpty()) {
            print("your delivery options are:\n" + deliveryOptions.toString());
            if (chooseYOrN("Wilt u nog een optie toevoegen?(y/n)")) {
                addDeliveryOptions();
            }
        }
    }
}
