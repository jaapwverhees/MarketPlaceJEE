package com.model;

import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

class RegistredVisitorTest {

    RegistredVisitor visitor;
    ArrayList<DeliveryOption> array;

    @BeforeEach
    void setUp() {
        array = new ArrayList<>();
        array.add(DeliveryOption.PICKUPFROMHOME);
        try {
            visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 12, "A", "0000AZ");
        } catch (CustomException e) {
            fail();
        }
    }

    @Test
    public void callAllConstructorsInvalidEmail(){
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array, "street", 12, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array, "street", 12, "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }
    public void callConstructorsInvalidStreetNumberZero(){
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@jaap.com", array, "street", 0, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@jaap.com", array, "street", 0, "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    public void callConstructorsInvalidEmptyArrayList(){
        ArrayList<DeliveryOption> array = new ArrayList<>();
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@jaap.com", array, "street", 0, "A", "0000AZ"));
        Assertions.assertEquals("Must contain DeliveryOption", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@jaap.com", array, "street", 0, "0000AZ"));
        Assertions.assertEquals("Must contain DeliveryOption", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@jaap.com", array));
        Assertions.assertEquals("Must contain DeliveryOption", exception.getMessage());
    }

    @Test
    void setInvalidEmailAddressNoSuffix() {
        Throwable exception = assertThrows(Exception.class, () -> visitor.setEmail("test@test"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    void setInvalidEmailAddressNoAtSign() {
        Throwable exception = assertThrows(Exception.class, () -> visitor.setEmail("testtestl.nl"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    void setInvalidEmailAddressNothingInFrontOfAtSign() {
        Throwable exception = assertThrows(Exception.class, () -> visitor.setEmail("@.nl"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    void invalidObjectCreationEmptyArray() {
        array = new ArrayList<>();
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 12, "A", "0000AZ"));
        Assertions.assertEquals("Must contain DeliveryOption", exception.getMessage());
    }

    @Test
    void invalidObjectCreationNegativeStreetNumber() {
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", -1, "A", "0000AZ"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());
    }

    @Test
    void invalidObjectCreationStreetNumberIsZero() {
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 0, "A", "0000AZ"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());
    }

    @Test
    void invalidObjectCreationInvalidEmail() {
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "@japie.com", array, "street", 1, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "test@test", array, "street", 1, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "test@", array, "street", 1, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "@test.nl", array, "street", 1, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    void invalidSetStreetNumberNegativeNumber() {
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", -1, "A", "0000AZ"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());
    }

    @Test
    void invalidSetStreetNumberIsZero() {
        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 0, "A", "0000AZ"));
        Assertions.assertEquals("Streetnumber cannot be lower then 1", exception.getMessage());
    }

}