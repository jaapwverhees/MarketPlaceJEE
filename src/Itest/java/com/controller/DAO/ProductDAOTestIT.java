package com.controller.DAO;

import com.App;
import com.controller.ProductController;
import com.model.AbstractEntity;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.Product;
import com.util.exeptions.CustomException;
import com.util.password.PasswordAuthentication;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RunWith(Arquillian.class)
public class ProductDAOTestIT {

    @Inject
    ProductDAO dao;

    @Inject
    VisitorDAO visitorDAO;


    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(App.class) // dont forget!
                .addPackage(ProductDAO.class.getPackage())
                .addPackage(ProductController.class.getPackage())
                .addPackage(AbstractEntity.class.getPackage())
                .addPackage(Product.class.getPackage())
                .addPackage(CustomException.class.getPackage())
                .addPackage(PasswordAuthentication.class.getPackage())
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml")
                .addAsManifestResource("META-INF/beans.xml", "beans.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @BeforeEach
    public void setUp() throws Exception {
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
        Visitor visitor = new Visitor(userName, email, deliveryOptions, streetName, streetNumber, suffix, zipcode);
        visitorDAO.createVisitor(visitor);


        BigDecimal bigDecimal = new BigDecimal("12.5");
        Product product = new Product("Article", "Description", visitor, deliveryOptions, categories, bigDecimal);
        dao.addArticle(product);

        bigDecimal = new BigDecimal("13");
        Product product2 = new Product("Article2", "Description2", visitor, deliveryOptions, categories, bigDecimal);
        dao.addArticle(product2);

        bigDecimal = new BigDecimal("15");
        Product product3 = new Product("Article3", "Description3", visitor, deliveryOptions, categories, bigDecimal);
        dao.addArticle(product3);
    }

    @Test
    public void getArticleByName() {
        List<Product> list = dao.getProductByName("Article");
        Assertions.assertEquals("Description", list.get(0).getDescription());
    }

    @Test
    public void getProductByCategory() {
        List<Product> list = dao.getProductByCategory("category");
        Assertions.assertEquals("Description", list.get(0).getDescription());
    }

    @Test
    public void getProductByPriceRangeReturnResultsValidMatches() {
        List<Product> list = dao.getProductByPriceRange(BigDecimal.valueOf(10.0), BigDecimal.valueOf(14.0));
        Assertions.assertEquals(2, list.size());
    }

    @Test
    public void getAllProductsReturnsThreeProducts() {
        List<Product> list = dao.getAllProducts();
        Assertions.assertEquals(3, list.size());
    }

    @Test
    public void getAllCategoryReturnOneResult() {
        Assertions.assertEquals(1, dao.getAllCategory().size());
    }

    @Test
    public void addANewValidCategory() throws Exception {
        dao.addCategory(new Category("new Category"));
        List<Category> categories = dao.getAllCategory();
        Assertions.assertEquals(2, categories.size());
        Assertions.assertTrue(categories.get(0).getDescription().equals("category") || categories.get(1).getDescription().equals("new Category"));
        Assertions.assertTrue(categories.get(1).getDescription().equals("category") || categories.get(1).getDescription().equals("new Category"));

    }
}
