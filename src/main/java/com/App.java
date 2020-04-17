package com;

import com.controller.RegisterVisitorController;
import com.model.DeliveryOption;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        ArrayList<DeliveryOption> options = new ArrayList<>();
        options.add(DeliveryOption.PICKUPFROMHOME);
        RegisterVisitorController controller = new RegisterVisitorController();
        controller.registerVisitor("tjapie", "jaap6@gmail.com", options, "Streetname", 12, "A", "5050AZ");
    }
}
