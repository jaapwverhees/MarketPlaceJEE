package com.model;

import com.util.exeptions.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class VisitorTest {

    private String userName = "userjaap";
    private String email = "test@test.nl";
    private Set<DeliveryOption> deliveryOptions;
    private String streetName = "streetname";
    private int streetNumber = 12;
    private String suffix = "A";
    private String zipcode = "5555AZ";

    @BeforeEach
    void setUp() {
        deliveryOptions = new HashSet<>();
        deliveryOptions.add(DeliveryOption.WAREHOUSE);
    }

    @Test
    void createInvalidCustomerNoDeliveryOptions() throws CustomException {
        deliveryOptions = new HashSet<>();

        Exception exception = assertThrows(CustomException.class, () -> {
            new Visitor(userName, email, deliveryOptions);
        });

        String expectedMessage = "Must contain DeliveryOption";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
    @Test
    void createInvalidCustomerInvalidEmail() throws CustomException {
        email = "invalid";

        Exception exception = assertThrows(CustomException.class, () -> {
            new Visitor(userName, email, deliveryOptions);
        });

        String expectedMessage = "String email is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void setEmail() throws CustomException {
        Visitor visitor = new Visitor(userName, email, deliveryOptions);

        Exception exception = assertThrows(CustomException.class, () -> {
            visitor.setEmail("blaboa");
        });

        String expectedMessage = "String email is invalid";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addInvalidDeliveryOptionNoAddressGiven() throws CustomException {

        Visitor visitor = new Visitor(userName, email, deliveryOptions);

        Exception exception = assertThrows(CustomException.class, () -> {
            visitor.addDeliveryOption(DeliveryOption.PICKUPFROMHOME);
        });

        String expectedMessage = "must contain address when choosing pickup from home";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void InvalidRemoveDeliveryOptionNoOptionsRemaining() throws CustomException {

        Visitor visitor = new Visitor(userName, email, deliveryOptions);

        Exception exception = assertThrows(CustomException.class, () -> {
            visitor.removeDeliveryOption(DeliveryOption.WAREHOUSE);
        });

        String expectedMessage = "must at least contain one delivery method";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}