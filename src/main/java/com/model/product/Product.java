package com.model.product;

import com.model.AbstractEntity;
import com.model.DeliveryOption;
import com.model.Visitor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.EnumType.STRING;

@Entity
public class Product extends AbstractEntity {

    private String name;

    private String description;

    @ManyToOne
    private Visitor supplier;

    @ElementCollection(targetClass = DeliveryOption.class)
    @Enumerated(STRING)
    private Set<DeliveryOption> deliveryOptions;

    private BigDecimal price;

    @ManyToMany(cascade = ALL)
    private Set<Category> categories = new HashSet<>();

    @OneToMany
    private Set<Multimedia> multimedia = new HashSet<>();

    public Product() {
    }

    public Product(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.deliveryOptions = deliveryOptions;
        this.categories = categories;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Set<DeliveryOption> getDeliveryOptions() {
        return deliveryOptions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Visitor getSupplier() {
        return supplier;
    }

    public void setSupplier(Visitor supplier) {
        this.supplier = supplier;
    }

}
