package com;

import com.controller.DAO.ProductDAO;
import com.controller.DAO.VisitorDAO;
import com.controller.ProductController;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.PriceType;
import com.model.product.Product;
import com.model.product.Service;
import com.util.exeptions.CustomException;
import com.view.CreateArticle;
import com.view.SearchProducts;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Init {
    private ProductDAO productDAO = new ProductDAO();
    private VisitorDAO visitorDAO = new VisitorDAO();
    private Set<DeliveryOption> deliveryOptions = new HashSet<>();
    private Set<Category> categories= new HashSet<>();

    public void start() throws CustomException {
        deliveryOptions.add(DeliveryOption.WAREHOUSE);
        Visitor visitor = new Visitor("jaapie", "test@test.nl",deliveryOptions);
        visitor.setPassword("MyPassword");
        try {
            visitorDAO.createVisitor(visitor);
            Category electronics = new Category("Electronics");
            Category toys = new Category("Toys");
            productDAO.addCategory(toys);
            productDAO.addCategory(electronics);
            Set<Category> categoryList= new HashSet<>();
            categoryList.add(toys);
            productDAO.addArticle(new Product("phone", "a phone", visitorDAO.getVisitor("test@test.nl"), deliveryOptions, categories, BigDecimal.valueOf(230.99)));
            categoryList= new HashSet<>();
            categoryList.add(electronics);
            productDAO.addArticle(new Product("phone", "a phone", visitorDAO.getVisitor("test@test.nl"), deliveryOptions, categoryList, BigDecimal.valueOf(230.99)));

            productDAO.addArticle(new Service("phoneRepair", "phoneRepair", visitorDAO.getVisitor("test@test.nl"), deliveryOptions, categoryList, BigDecimal.valueOf(230.99), PriceType.hourlyRate));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        //TODO een entitymanager fac voor hele applicatie.
       //new SearchProducts().start();
        //new CreateArticle(visitor).start();
    }
}
