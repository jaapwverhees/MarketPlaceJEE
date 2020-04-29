package com.model;

import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AddressTest {

    @Test
    void createValidAdress() throws CustomException {
        Address address = new Address("street", 1, "suffix", "zip");
    }
    @Test
    void createInvalidAddressNegativeStreetNumber() {
        Exception exception = assertThrows(CustomException.class, () -> {
            new Address("street", 0, "suffix", "zip");
        });

        String expectedMessage = "Streetnumber cannot be lower then 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void createInvalidAddressNegativeStreetNumberNoSuffix() {
        Exception exception = assertThrows(CustomException.class, () -> {
            new Address("street", 0, "zip");
        });

        String expectedMessage = "Streetnumber cannot be lower then 1";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}