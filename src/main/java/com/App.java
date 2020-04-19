package com;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.RegisterVisitorController;
import com.controller.VisitorLoginController;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;

import java.util.*;

public class App {

    public static void main(String[] args) throws Exception {
//        ArrayList<DeliveryOption> options = new ArrayList<>();
//        options.add(DeliveryOption.PICKUPFROMHOME);
//        RegisterVisitorController controller = new RegisterVisitorController();
//        controller.registerVisitorWithAddressAndSuffix("tjapie", "userjaap@gmail.com", options, "Streetname", 12, "A", "5050AZ");

//        VisitorLoginController login = new VisitorLoginController();
//        System.out.println(login.login("userjaap@gmail.com", "uz6!19P^9y#v").getUserName());
        Set<DeliveryOption> set = new HashSet<>();
        set.add(DeliveryOption.WAREHOUSE);
        set.add(DeliveryOption.WAREHOUSE);
        set.add(DeliveryOption.PICKUPFROMHOME);
        System.out.println(set.toString());
    }
}
