package com.view;

import com.controller.VisitorLoginController;
import com.model.Visitor;
import javafx.scene.control.MenuItem;

import java.lang.reflect.Array;
import java.util.Scanner;

public class LoginMenu {

    private String password;

    private String email;

    private Scanner scanner = new Scanner(System.in);

    private VisitorLoginController controller = new VisitorLoginController();

    public Visitor login(){
        InputCredentials();
        try{
            Visitor visitor = controller.login(email,password);
            System.out.println("Valid credentials");
            return visitor;
        } catch(NullPointerException nullPointerException){
            System.out.println("Invalid credentials, returning to main menu...");
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
        }
        return null;
    }
    private void InputCredentials(){

        System.out.println("voor uw email adres in;");

        this.email = String.valueOf(scanner.nextLine());

        System.out.println("voor uw wachtwoord in;");

        this.password = String.valueOf(scanner.nextLine());

    }
}
