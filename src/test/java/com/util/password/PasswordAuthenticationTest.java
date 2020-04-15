package com.util.password;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

//TODO needs more and better testcases. also i dont quite understand the class yet. i would expect
//TODO that two tokens based on the same password are equal, but they dont seem to be.
class PasswordAuthenticationTest {
    private static final String PASSWORD = "password";
    private PasswordAuthentication aut = new PasswordAuthentication();

    @Test
    void canAuthenticateBasedOnAPassword() {
        assertTrue(aut.authenticate(PASSWORD.toCharArray(), aut.hash(PASSWORD)));
    }
    @Test
    void hashDoesMutateString(){
        assertNotEquals(PASSWORD, aut.hash(PASSWORD));
    }
}