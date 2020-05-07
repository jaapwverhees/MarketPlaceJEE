package com;

import com.view.MainMenu;

public class App {

    public static void main(String[] args) throws Exception {

        new Init().start();
        MainMenu mainMenu = new MainMenu();
        mainMenu.start();
    }
}
