package com.controller.DAO;

import com.model.Address;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.util.exeptions.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VisitorDAOTestNew {

    private VisitorDAO dao = new VisitorDAO(Persistence.createEntityManagerFactory("H2").createEntityManager());

    @BeforeEach
    void setup() throws Exception {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        dao.createVisitor(new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));

    }
    @Test
    void getVisitor() {
        Assertions.assertEquals("jaap", dao.getVisitor("Test@Testnotreal.com").getUserName());
    }

    @Test
    void createVisitor() {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        Exception exception = assertThrows(CustomException.class, () -> {
            dao.createVisitor(new Visitor("jaapfewfwefwefwefwefwefwfwefwefffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));
        });
    }
}