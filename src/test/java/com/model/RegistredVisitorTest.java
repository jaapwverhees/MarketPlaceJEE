package com.model;

import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistredVisitorTest {

    private RegistredVisitor visitor;

    private Set<DeliveryOption> array;

    @BeforeEach
    void setUp() throws CustomException {

        array = new HashSet<>();
        array.add(DeliveryOption.PICKUPFROMHOME);

        visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 12, "A", "0000AZ");
    }

    @Test
    public void callAllConstructorsInvalidEmail() {

        Throwable exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array, "street", 12, "A", "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array, "street", 12, "0000AZ"));
        Assertions.assertEquals("String email is invalid", exception.getMessage());

        exception = assertThrows(Exception.class, () -> visitor = new RegistredVisitor("jaap", "Jaapie@com", array));
        Assertions.assertEquals("String email is invalid", exception.getMessage());
    }

    @Test
    public void callConstructorsInvalidEmptyArrayList() {

        Set<DeliveryOption> array = new HashSet<>();
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

        array = new HashSet<>();
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

    @Test()
    void CannotremoveDeliveryOptionifLessThenTwo() throws CustomException {

        visitor = new RegistredVisitor("jaap", "test@test.com", array, "street", 1, "A", "0000AZ");
        Throwable exception = assertThrows(Exception.class, () -> visitor.removeDeliveryOption(DeliveryOption.WAREHOUSE));

        assertEquals("must at least contain one delivery method", exception.getMessage());
    }
    @Test
    void cannotChoosePICKUPFROMHOMEWithoutAddressConstructor(){

        array.add(DeliveryOption.PICKUPFROMHOME);
        Throwable exception = assertThrows(Exception.class, () -> new RegistredVisitor("jaap", "test@test.com", array));

        assertEquals("For home pick an address must be given.", exception.getMessage());
    }

    @Test
    void cannotSetPICKUPFROMHOMEWithoutAddressMethod() throws CustomException {

        array.remove(DeliveryOption.PICKUPFROMHOME);
        array.add(DeliveryOption.WAREHOUSE);

        visitor = new RegistredVisitor("jaap", "test@test.com", array);

        Throwable exception = assertThrows(Exception.class, () -> visitor.addDeliveryOption(DeliveryOption.PICKUPFROMHOME));

        assertEquals("must contain address when choosing pickup from home", exception.getMessage());
    }
}
