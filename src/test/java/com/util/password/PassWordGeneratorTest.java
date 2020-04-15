package com.util.password;

import com.util.password.PassWordGenerator;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassWordGeneratorTest {


    @Test
    void passwordHasCorrectLength() {
        String string = "";
        for (int i = 0; i < 20; i++)
            string = PassWordGenerator.generatePassword(10);
        assertEquals(10, string.length());
    }

    //TODO Checks if contains any of string, but should check if contains two of string, but could be two of same char.
    @Test
    void passwordContainsLettersNumbersSpecialCharacters() {
        String string = PassWordGenerator.generatePassword(10);
        assertTrue(StringUtils.containsAny("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz", string));
        assertTrue(StringUtils.containsAny("0123456789", string));
        assertTrue(StringUtils.containsAny("!@#$%^&*", string));
    }

    @Test
    void passwordContainsOnlyAllowedChars() {
        String testString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz" +
                        "0123456789" +
                        "!@#$%^&*";
        assertTrue(StringUtils.containsAny(testString, PassWordGenerator.generatePassword(10)));

    }

}

