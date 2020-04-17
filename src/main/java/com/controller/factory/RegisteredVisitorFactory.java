package com.controller.factory;

import com.model.DeliveryOption;
import com.model.RegistredVisitor;

import java.util.ArrayList;

public class RegisteredVisitorFactory {

    public RegistredVisitor create(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) throws Exception {
        if (streetName.equals("") && streetNumber == 0 && zipcode.equals("")) {
            return new RegistredVisitor(userName, email, deliveryOptions);
        } else if (suffix.equals("")) {
            return new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, zipcode);
        }
        return new RegistredVisitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
    }
}
