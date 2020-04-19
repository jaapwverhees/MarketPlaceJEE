package com.controller;

import com.controller.DAO.RegisteredVisitorDAO;
import com.controller.DAO.RegisteredVisitorDAOable;
import com.model.DeliveryOption;
import com.model.RegistredVisitor;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;


//TODO How to test. I can mock DB responses, but i would like to also test the private methods,
// but for them to remain private
class RegisterVisitorControllerTest {


    RegisteredVisitorDAOable DOA = mock(RegisteredVisitorDAO.class);

    RegisterVisitorController controller = new RegisterVisitorController();


    @BeforeEach
    void setUp() throws Exception {
        Set<DeliveryOption> array = new HashSet<>();
        array.add(DeliveryOption.PICKUPFROMHOME);
        RegistredVisitor visitor = new RegistredVisitor("jaap", "Jaapie@japie.com", array, "street", 12, "A", "0000AZ");
        doNothing().when(DOA).addRegistredVisitor(visitor);
        controller.setRegisteredVisitorDAO(DOA);
    }

}