package com;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class main {
    public static void main(String[] args) throws Exception {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();
        App app = container.select(App.class).get();
        app.start();
    }
}
