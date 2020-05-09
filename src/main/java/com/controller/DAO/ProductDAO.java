package com.controller.DAO;

import com.model.product.Category;
import com.model.product.Product;
import com.util.producers.EntityManagerProducer;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class ProductDAO {

    private EntityManager entityManager = EntityManagerProducer.getEntityManager();

    public ProductDAO() {}

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addArticle(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public List<Product> getProductByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = : name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Product> getProductByCategory(String description) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.description = :description", Product.class);
        query.setParameter("description", description);
        return query.getResultList();
    }

    public List<Product> getProductByPriceRange(BigDecimal minimum, BigDecimal maximum) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minimum AND :maximum ORDER BY p.price", Product.class);
        query.setParameter("minimum", minimum);
        query.setParameter("maximum", maximum);
        return query.getResultList();
    }

    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    public List<Category> getAllCategory() {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    //TODO admin validation
    public void addCategory(Category category) {
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
    }
}
