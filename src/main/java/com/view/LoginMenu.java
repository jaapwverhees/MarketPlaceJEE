package com.view;

import com.controller.VisitorLoginController;
import com.model.Visitor;
import sun.rmi.runtime.Log;

import java.util.Scanner;

public class LoginMenu {

    private String password;

    private String email;

    private Scanner scanner = new Scanner(System.in);

    private VisitorLoginController controller = new VisitorLoginController();

    public void login() {
        InputCredentials();

        Visitor visitor = controller.login(email, password);
        if (visitor == null) {
            System.out.println("Invalid credentials, returning to main menu...");
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
        } else{
            new LoggedInMenu(visitor).start();
        }

    }

    private void InputCredentials() {

        System.out.println("voor uw email adres in;");

        this.email = String.valueOf(scanner.nextLine());

        System.out.println("voor uw wachtwoord in;");

        this.password = String.valueOf(scanner.nextLine());

    }
}
