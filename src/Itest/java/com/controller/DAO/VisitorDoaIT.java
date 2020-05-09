package com.controller.DAO;

import com.App;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.util.exeptions.CustomException;
import org.jboss.weld.junit5.auto.AddBeanClasses;
import org.jboss.weld.junit5.auto.AddPackages;
import org.jboss.weld.junit5.auto.EnableAlternatives;
import org.jboss.weld.junit5.auto.EnableAutoWeld;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class VisitorDoaIT {

    @Mock
    Logger log;

    @InjectMocks
    private VisitorDAO dao = new VisitorDAO(Persistence.createEntityManagerFactory("H2").createEntityManager());

    @Test
    void addValidVisitor() throws Exception {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        dao.createVisitor(new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));
        Assertions.assertEquals("jaap", dao.getVisitor("Test@Testnotreal.com").getUserName());
    }

    @Test
    void VisitorGetsHashedPassword() throws Exception {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        Visitor visitor = new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ");
        visitor.setPassword("thisIsANewPassword");
        dao.createVisitor(visitor);
        Assertions.assertNotEquals("thisIsANewPassword", dao.getVisitor("Test@Testnotreal.com").getUserName());
    }
    @Test
    void CannotCreateTwoVisitorsSameEmail() throws Exception {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.WAREHOUSE);
        dao.createVisitor(new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));

        Exception exception = assertThrows(CustomException.class, () -> {
            dao.createVisitor(new Visitor("jaap", "Test@Testnotreal.com", array, "street", 12, "A", "0000AZ"));
        });
        assertEquals("Emailadres is al in gebruik", exception.getMessage());
    }
}