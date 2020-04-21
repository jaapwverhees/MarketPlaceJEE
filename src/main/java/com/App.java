package com;

import com.controller.DAO.VisitorDAO;
import com.model.DeliveryOption;
import com.model.Visitor;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    public static void main(String[] a) throws Exception {

        EntityManager entityManager = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

        VisitorDAO dao = new VisitorDAO(entityManager);

        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);

        dao.addRegistredVisitor(new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));
        Visitor person = dao.getRegisteredVisitor("Test@Testnotreal.com");
        System.out.println(person.getAddress().getStreetNumber());
    }
}
