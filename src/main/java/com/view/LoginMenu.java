package com.view;

import com.controller.VisitorLoginController;
import com.model.Visitor;

import static com.view.util.ControllerService.getVisitorLoginController;
import static com.view.util.Print.print;
import static com.view.util.UserInputReader.inputString;

public class LoginMenu {

    private final VisitorLoginController controller = getVisitorLoginController();
    private String password;
    private String email;

    public void login() {
        InputCredentials();

        Visitor visitor = controller.login(email, password);
        if (visitor == null) {
            print("Invalid credentials, returning to main menu...");
            MainMenu mainMenu = new MainMenu();
            mainMenu.start();
        } else {
            new LoggedInMenu(visitor).start();
        }

    }

    private void InputCredentials() {

        this.email = inputString("voor uw email adres in;");

        this.password = inputString("voor uw wachtwoord in;");
    }
}
