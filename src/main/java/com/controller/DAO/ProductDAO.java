package com.controller.DAO;

import com.model.product.Category;
import com.model.product.Product;
import com.util.exeptions.CustomException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

import static javax.ejb.TransactionAttributeType.REQUIRED;
import static javax.ejb.TransactionAttributeType.REQUIRES_NEW;

@Stateless // needed to make it EJB
public class ProductDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public ProductDAO() {
    }


    //TODO credentials should be checked (better) via session token or something likewise;
    @TransactionAttribute(REQUIRES_NEW)
    public void addArticle(Product product) {
        entityManager.persist(product);
    }

    @TransactionAttribute(REQUIRES_NEW)
    public void deleteArticle(Product product) {
        entityManager.remove(product);
    }

    @TransactionAttribute(REQUIRED)
    public List<Product> getProductByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = : name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public List<Product> getProductByCategory(String description) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.description = :description", Product.class);
        query.setParameter("description", description);
        return query.getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public List<Product> getProductByPriceRange(BigDecimal minimum, BigDecimal maximum) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.price BETWEEN :minimum AND :maximum ORDER BY p.price", Product.class);
        query.setParameter("minimum", minimum);
        query.setParameter("maximum", maximum);
        return query.getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public List<Product> getAllProducts() {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p", Product.class);
        return query.getResultList();
    }

    @TransactionAttribute(REQUIRED)
    public List<Category> getAllCategory() {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    //TODO admin validation
    @TransactionAttribute(REQUIRES_NEW)
    public void addCategory(Category category) {
        entityManager.persist(category);
    }


    //TODO credentials should be checked (better) via session token or something likewise;
    @TransactionAttribute(REQUIRES_NEW)
    public void updateProduct(Product product) throws CustomException {
        Product entity = entityManager.find(Product.class, product.getId());
        if (entity == null) {
            throw new CustomException("product id does not exist");
        } else if (!product.getSupplier().getUserName().equals(entity.getSupplier().getUserName())) {
            throw new CustomException("invalid customer");
        } else {
            entityManager.getTransaction().begin();
            entityManager.merge(product);
            entityManager.getTransaction().commit();
        }
    }
}
