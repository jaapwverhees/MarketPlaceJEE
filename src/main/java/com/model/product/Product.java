package com.model.product;

import com.model.AbstractEntity;
import com.model.DeliveryOption;
import com.model.Visitor;

import javax.persistence.ElementCollection;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@MappedSuperclass
public abstract class Product extends AbstractEntity {

    private String name;

    private String description;

//    @ElementCollection(targetClass = Object.class)
//    private ArrayList<Object> multimedia;

    @ManyToOne
    private Visitor supplier;

    @ElementCollection(targetClass = DeliveryOption.class)
    @Enumerated(STRING)
    private Set<DeliveryOption> deliveryOptions;

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
