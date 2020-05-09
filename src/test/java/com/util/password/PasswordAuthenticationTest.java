package com.util.password;

import com.model.Visitor;
import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordAuthenticationTest {

    PasswordAuthentication passwordAuthentication = new PasswordAuthentication();

    @Test
    void canHashString() {
        assertNotEquals(passwordAuthentication.hash("theNewpassword"), "theNewpassword");
    }

    @Test
    void CanAuthenticatePassword() {
        String token = passwordAuthentication.hash("theNewpassword");
        assertTrue(passwordAuthentication.authenticate("theNewpassword".toCharArray(), token));
    }

    @Test
    void DontAuthenticatePassword() {
        String token = passwordAuthentication.hash("theCorrectpassword");
        assertFalse(passwordAuthentication.authenticate("theNewpassword".toCharArray(), token));
    }

}