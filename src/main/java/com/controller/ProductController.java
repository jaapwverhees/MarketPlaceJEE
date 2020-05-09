package com.controller;

import com.controller.DAO.ProductDAO;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.PriceType;
import com.model.product.Product;
import com.model.product.Service;
import com.util.exeptions.CustomException;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class ProductController {

    private final Logger errorLogger = getLogger(this.getClass());

    @SuppressWarnings("FieldMayBeFinal")
    private ProductDAO dao = new ProductDAO();

    public String addArticle(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price) {
        try {
            Product product = new Product(name, description, supplier, deliveryOptions, categories, price);
            dao.addArticle(product);
        } catch (Exception e) {
         return exceptionHandler(e);
        }
        return "toevoegen product succesvol";
    }

    public String addService(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price, PriceType priceType) {
        try {
            Service product = new Service(name, description, supplier, deliveryOptions, categories, price, priceType);
            dao.addArticle(product);
        } catch (Exception e) {
            return exceptionHandler(e);
        }
        return "toevoegen product succesvol";
    }
    public List<Product> getProductsByName(String name){
        return dao.getProductByName(name);
    }

    public List<Product> getProductsByPrice(BigDecimal minimum, BigDecimal maximum){
        return dao.getProductByPriceRange(minimum, maximum);
    }

    public List<Product> getProductsByCategory(String description){
        return dao.getProductByCategory(description);
    }

    public List<Product> getAllProducts(){return dao.getAllProducts();}

    public List<Category> getAllCategories(){
        return dao.getAllCategory();
    }

    private String exceptionHandler(Exception e) {
        return "Er heeft een overwachte fout plaatsgevonden: " + e.getMessage();
    }
}
