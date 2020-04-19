package com.model;

import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    @Test
    public void callConstructorsInvalidStreetNumberZero() {

        Throwable exception = assertThrows(Exception.class, () -> new Address("StreetName", 0, "A", "5050"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());

        exception = assertThrows(Exception.class, () -> new Address("StreetName", 0, "5050"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());
    }
    @Test
    public void callSetStreetNumberMethodInvalidStreetNumberZero() throws CustomException {

        Address address = new Address("StreetName", 1, "A", "5050");
        Throwable exception = assertThrows(Exception.class, () -> address.setStreetNumber(0));

        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());

    }
}