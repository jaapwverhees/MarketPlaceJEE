package com.controller;

import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.Product;

import java.math.BigDecimal;
import java.util.Set;

public class ProductController {

    public String addArticle(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price) {
        try {
            Product product = new Product(name, description, supplier, deliveryOptions, categories, price);
        } catch (Exception e) {

        }

        return "";
    }
}
