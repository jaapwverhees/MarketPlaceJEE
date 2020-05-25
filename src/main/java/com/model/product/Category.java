package com.model.product;

import com.model.AbstractEntity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Category extends AbstractEntity {

    private String description;

    @ManyToMany(mappedBy = "categories")
    Set<Product> products = new HashSet<>();

    public Category() {
    }


    public Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(Product product){
        products.remove(product);
    }
    public String toString(){
        return description;
    }
}
