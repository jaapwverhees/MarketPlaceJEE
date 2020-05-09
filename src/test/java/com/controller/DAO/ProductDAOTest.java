package com.controller.DAO;

import com.model.product.Category;
import com.model.product.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductDAOTest {

    @Mock
    TypedQuery<Product> queryProductMock;

    @Mock
    TypedQuery<Category> queryCategoryMock;
    List<Product> products = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    @Mock
    private EntityManager emMock;
    @Mock
    private EntityTransaction entityTransactionMock;
    @InjectMocks
    private final ProductDAO dao = new ProductDAO();

    @BeforeEach
    void setup() {

    }

    @Test
    void addArticle() {
    }

    @Test
    void getProductByName() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(queryProductMock);

        when(queryProductMock.getResultList()).thenReturn(products);

        dao.getProductByName("test");

        verify(emMock).createQuery(anyString(), eq(Product.class));
        verify(queryProductMock).setParameter(anyString(), any());
        verify(queryProductMock).getResultList();
    }

    @Test
    void getProductByCategory() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(queryProductMock);

        when(queryProductMock.getResultList()).thenReturn(products);

        dao.getProductByCategory("test");

        verify(emMock).createQuery(anyString(), eq(Product.class));
        verify(queryProductMock).setParameter(anyString(), any());
        verify(queryProductMock).getResultList();
    }

    @Test
    void getProductByPriceRange() {
        when(emMock.createQuery(anyString(), eq(Product.class))).thenReturn(queryProductMock);

        when(queryProductMock.getResultList()).thenReturn(products);

        dao.getProductByPriceRange(BigDecimal.valueOf(10.0), BigDecimal.valueOf(11.0));

        verify(emMock).createQuery(anyString(), eq(Product.class));
        verify(queryProductMock, atLeastOnce()).setParameter(anyString(), any());
        verify(queryProductMock).getResultList();
    }


    @Test
    void getAllCategory() {
        when(emMock.createQuery(anyString(), eq(Category.class))).thenReturn(queryCategoryMock);

        when(queryCategoryMock.getResultList()).thenReturn(categories);

        dao.getAllCategory();

        verify(emMock).createQuery(anyString(), eq(Category.class));
        verify(queryCategoryMock).getResultList();
    }


    @Test
    void addCategory() {
        when(emMock.getTransaction()).thenReturn(entityTransactionMock);
        doNothing().when(entityTransactionMock).begin();
        doNothing().when(entityTransactionMock).commit();

        dao.addCategory(new Category("test"));

        verify(emMock).persist(isA(Category.class));
        verify(emMock, atLeastOnce()).getTransaction();
        verify(entityTransactionMock).begin();
        verify(entityTransactionMock).commit();
    }
}