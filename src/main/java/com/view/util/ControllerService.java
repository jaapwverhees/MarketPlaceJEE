package com.view.util;

import com.controller.DAO.ProductDAO;
import com.controller.ProductController;
import com.controller.VisitorController;
import com.controller.VisitorLoginController;
import com.model.product.Product;

public class ControllerService {

    private static final ProductController productController = new ProductController();

    private static final VisitorController visitorController = new VisitorController();

    private static final VisitorLoginController visitorLoginController = new VisitorLoginController();

    public static ProductController getProductController() {
        return productController;
    }

    public static VisitorController getVisitorController() {
        return visitorController;
    }

    public static VisitorLoginController getVisitorLoginController() {
        return visitorLoginController;
    }

}
