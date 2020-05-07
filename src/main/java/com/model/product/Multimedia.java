package com.model.product;

import com.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Enumerated;

import static javax.persistence.EnumType.STRING;

@Entity
public class Multimedia extends AbstractEntity {

    @Enumerated(STRING)
    private MediaType type;

    private byte[] content;

    public Multimedia(){}

    public Multimedia(MediaType type, byte[] content) {
        this.type = type;
        this.content = content;
    }

    public MediaType getType() {
        return type;
    }

    public byte[] getContent() {
        return content;
    }
}
