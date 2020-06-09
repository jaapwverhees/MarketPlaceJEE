package com.model.product;

import com.model.DeliveryOption;
import com.model.Visitor;

import javax.persistence.Entity;
import java.math.BigDecimal;
import java.util.Set;

@Entity
public class Service extends Product {

    private PriceType priceType;


    public Service() {
    }

    public Service(String name, String description, Visitor supplier, Set<DeliveryOption> deliveryOptions, Set<Category> categories, BigDecimal price, PriceType priceType) {
        super(name, description, supplier, deliveryOptions, categories, price);
        this.priceType = priceType;
    }

    public PriceType getPriceType() {
        return priceType;
    }

    public void setPriceType(PriceType priceType) {
        this.priceType = priceType;
    }

}
