package com.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.model.AbstractEntity;
import com.model.DeliveryOption;
import com.model.Visitor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product extends AbstractEntity {

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @ManyToOne
    private Visitor supplier;

    @ElementCollection(targetClass = DeliveryOption.class, fetch = EAGER)
    @Enumerated(STRING)
    private Set<DeliveryOption> deliveryOptions;

    @NotNull
    private BigDecimal price;

    @NotNull
    @ManyToMany(cascade = ALL, fetch = EAGER)
    private Set<Category> categories = new HashSet<>();

    //TODO implement
    @OneToMany(fetch = EAGER)
    private Set<Multimedia> multimedia = new HashSet<>();

    //TODO implement all fields
    public Product(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.supplier = supplier;
        this.deliveryOptions = deliveryOptions;
        this.categories = categories;
        this.price = price;
    }
    //TODO troubleshoot
    public boolean valid() {
        return nameIsValid() &&
                descriptionIsValid() &&
                //deliveryOptionsIsValid() &&
                //categoriesIsValid() &&
                priceIsValid();
    }

    private boolean priceIsValid() {
        return this.price.compareTo(BigDecimal.ZERO) > 0;
    }

    private boolean categoriesIsValid() {
        return !categories.isEmpty();
    }

    private boolean deliveryOptionsIsValid() {
        return supplier.getDeliveryOptions().containsAll(deliveryOptions);
    }

//    private boolean supplierIsValid() {
//        return this.supplier.valid();
//    }

    private boolean descriptionIsValid() {
        return this.description.length() > 5;
    }

    private boolean nameIsValid() {
        return this.name.length() > 3;
    }
}
