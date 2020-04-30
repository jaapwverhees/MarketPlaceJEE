package com;

import com.view.CreateAccount;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class App {

    public static void main(String[] args) throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        CreateAccount createAccount = container.select(CreateAccount.class).get();
        createAccount.createAccount();
    }
}
