package com.controller.DAO;

import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.Product;
import org.h2.util.json.JSONValidationTargetWithoutUniqueKeys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

class ProductDAOTest {


    private Visitor visitor;

    private ProductDAO dao = new ProductDAO(Persistence.createEntityManagerFactory("MySQL").createEntityManager());

    private Product product;

    private VisitorDAO visitorDAO = new VisitorDAO(Persistence.createEntityManagerFactory("MySQL").createEntityManager());

    @BeforeEach
    void setUp() throws Exception {
        String userName = "userjaap";
        String email = "test@test.nl";
        Set<DeliveryOption> deliveryOptions;
        deliveryOptions = new HashSet<>();
        deliveryOptions.add(DeliveryOption.WAREHOUSE);
        String streetName = "streetname";
        int streetNumber = 12;
        String suffix = "A";
        String zipcode = "5555AZ";
        Set<Category> categories = new HashSet<>();
        categories.add(new Category("category"));
        visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
        BigDecimal bigDecimal = new BigDecimal("12.5");
        product = new Product("Article", "Description", visitor, deliveryOptions, categories, bigDecimal);

        visitorDAO.createVisitor(visitor);
        dao.addArticle(product);
    }

    @Test
    void getArticleByName() {
        List<Product> list = dao.getProductByName("Article");
        Assertions.assertEquals("Description", list.get(0).getDescription());
    }

    @Test
    void getProductByCategory() {
        List<Product> list = dao.getProductByCategory(2);
        System.out.println(list.get(0).getDescription());
    }
}