package com.model.product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity
public class Article extends Product {

    @ElementCollection(targetClass = Category.class)
    @Enumerated(STRING)
    //@NotNull
    private Set<Category> Categories;

    private BigDecimal price;

    public Set<Category> getCategories() {
        return Categories;
    }

    public void setCategories(Set<Category> categories) {
        Categories = categories;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
