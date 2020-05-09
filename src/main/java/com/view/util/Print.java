package com.view.util;

public class Print {

    static void print(String text){
        System.out.print(text);
    }

    static void print(String format, Object ... args){
        System.out.printf(format, args);
    }
}
