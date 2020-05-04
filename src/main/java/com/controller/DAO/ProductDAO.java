package com.controller.DAO;

import com.model.product.Product;
import com.util.password.PasswordAuthentication;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class ProductDAO {

    private PasswordAuthentication aut = new PasswordAuthentication();

    private EntityManager entityManager = Persistence.createEntityManagerFactory("MySQL").createEntityManager();

    public ProductDAO() {
    }

    public ProductDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void addArticle(Product product) throws Exception {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    public List<Product> getProductByName(String name) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p WHERE p.name = : name", Product.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Product> getProductByCategory(int id) {
        TypedQuery<Product> query = entityManager.createQuery("SELECT p FROM Product p JOIN p.categories c WHERE c.id = : id", Product.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
}
