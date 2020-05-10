package com.view.util;

public class Print {

    public static void print(String text) {
        System.out.print(text);
    }

    public static void print(String format, Object... args) {
        System.out.printf(format, args);
    }

    public static void print() {
        System.out.println();
    }

}
