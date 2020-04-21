package com.controller.DAO;

import com.model.DeliveryOption;
import com.model.Visitor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitorDAOTest {

    private  Visitor visitor;
    VisitorDAO dao = new VisitorDAO(Persistence.createEntityManagerFactory("H2").createEntityManager());

    @BeforeEach
    void setup() throws Exception {
        try{
            Set<DeliveryOption> array = new HashSet<>(Arrays.asList(DeliveryOption.WAREHOUSE));
            visitor = new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ");
            dao.addRegistredVisitor(visitor);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    void getRegisteredVisitor() throws Exception {
        visitor = new Visitor("jaap", "Test@Testnotreal.com", new HashSet<>(Arrays.asList(DeliveryOption.WAREHOUSE)), "street", 12, "A", "0000AZ");
        dao.addRegistredVisitor(visitor);
        dao.getRegisteredVisitor("Test@Testnotreal.com");
    }

    @Test
    void addRegistredVisitor() {
    }
}