package com.controller;

import com.controller.DAO.ProductDAO;
import com.model.DeliveryOption;
import com.model.Visitor;
import com.model.product.Category;
import com.model.product.PriceType;
import com.model.product.Product;
import com.util.exeptions.CustomException;
import org.hibernate.id.AbstractPostInsertGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static com.model.product.PriceType.hourlyRate;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.willAnswer;
import static org.mockito.Mockito.*;
import static org.slf4j.LoggerFactory.getLogger;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private Logger errorLogger;

    @Mock
    private ProductDAO dao;

    @InjectMocks
    private final ProductController controller = new ProductController();

    @Test
    void addValidService(){
        assertEquals("toevoegen product succesvol",
                controller.addService(
                        "test",
                        "test",
                        new Visitor(),
                        new HashSet<DeliveryOption>(),
                        new HashSet<Category>(),
                        new BigDecimal("10.0"),
                        hourlyRate));
        verify(dao).addArticle(any());
    }

    @Test
    void addInvalidService(){
        doThrow(new RuntimeException("test")).when(dao).addArticle(any(Product.class));

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test", controller.addService(
                "test",
                "test",
                new Visitor(),
                new HashSet<DeliveryOption>(),
                new HashSet<Category>(),
                new BigDecimal("10.0"),
                hourlyRate));

        verify(dao).addArticle(any());
    }

    @Test
    void addValidArticle() {
        assertEquals("toevoegen product succesvol",
                controller.addArticle(
                        "test",
                        "test",
                        new Visitor(),
                        new HashSet<DeliveryOption>(),
                        new HashSet<Category>(),
                        new BigDecimal("10.0")));
        verify(dao).addArticle(any());
    }
    @Test
    void addInvalidArticle() {
        doThrow(new RuntimeException("test")).when(dao).addArticle(any(Product.class));

        assertEquals("Er heeft een overwachte fout plaatsgevonden: test", controller.addArticle(
                "test",
                "test",
                new Visitor(),
                new HashSet<DeliveryOption>(),
                new HashSet<Category>(),
                new BigDecimal("10.0")));

        verify(dao).addArticle(any());
    }

    @Test
    void validGetProductsByName() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(dao.getProductByName("test")).thenReturn(products);

        List<Product> list = controller.getProductsByName("test");

        verify(dao).getProductByName("test");
        Assertions.assertEquals(list.size(), 1);
    }

    @Test
    void validGetProductsByPrice() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        BigDecimal min = new BigDecimal("1.0");
        BigDecimal max = new BigDecimal("2.0");

        when(dao.getProductByPriceRange(min, max)).thenReturn(products);
        assertEquals(1, controller.getProductsByPrice(min, max).size());
        verify(dao).getProductByPriceRange(min, max);
    }

    @Test
    void ValidGetProductsByCategory() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(dao.getProductByCategory("WAREHOUSE")).thenReturn(products);
        assertEquals(1, controller.getProductsByCategory("WAREHOUSE").size());
    }

    @Test
    void getAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(new Product());

        when(dao.getAllProducts()).thenReturn(products);
        assertEquals(1,controller.getAllProducts().size());
    }

    @Test
    void getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categories.add(new Category());

        when(dao.getAllCategory()).thenReturn(categories);

        assertEquals(1, controller.getAllCategories().size());
    }
}