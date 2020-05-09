package com.view.util;

import com.controller.ProductController;
import com.controller.VisitorController;
import com.controller.VisitorLoginController;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerServiceTest {

    @Test
    void getProductControllerReturnsProductController() {
        assertEquals(ControllerService.getProductController().getClass(), ProductController.class);
    }

    @Test
    void getVisitorControllerReturnsVisitorController() {
        assertEquals(ControllerService.getVisitorController().getClass(), VisitorController.class);
    }

    @Test
    void getVisitorLoginControllerReturnsVisitorController() {
        assertEquals(ControllerService.getVisitorLoginController().getClass(), VisitorLoginController.class);
    }
}