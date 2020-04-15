package com.util.password;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PassWordGenerator {

    private static final Random RANDOM = new SecureRandom();
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*";

    public static String generatePassword(int length) {
        String returnValue = "";
        int[] stringLengths = generateThreeNumberTotalOfLenght(length);
        returnValue += GenerateRandomStringOfTypeAndLenght(stringLengths[0], ALPHABET);
        returnValue += GenerateRandomStringOfTypeAndLenght(stringLengths[1], NUMBERS);
        returnValue += GenerateRandomStringOfTypeAndLenght(stringLengths[2], SPECIAL_CHARS);
        return shuffledString(returnValue);
    }

    private static StringBuilder GenerateRandomStringOfTypeAndLenght(int length, String type) {
        StringBuilder returnValue = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            returnValue.append(type.charAt(RANDOM.nextInt(type.length())));
        }
        return returnValue;
    }

    private static int[] generateThreeNumberTotalOfLenght(int total) {
        int[] array = {2, 2, 2};

        for (int i = 0; i < total - 6; i++) {
            array[RANDOM.nextInt(3)]++;
        }
        return array;
    }

    private static String shuffledString(String string) {
        List<Character> list = new ArrayList<>();
        for (char c : string.toCharArray()) {
            list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder builder = new StringBuilder();
        for (char c : list) {
            builder.append(c);
        }
        return builder.toString();
    }
}
