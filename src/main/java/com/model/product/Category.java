package com.model.product;

import com.model.AbstractEntity;

import javax.persistence.Entity;

@Entity
public class Category extends AbstractEntity {


    private String description;

    public Category() {

    }


    public Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
