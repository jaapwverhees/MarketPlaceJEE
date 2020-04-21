package com.model;

import com.util.exeptions.CustomException;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {

    public Address(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "adress_id")
    private int adress_id;
    private String streetName;
    private int streetNumber;
    private String suffix;
    private String zipcode;

    public Address(String streetName, int streetNumber, String suffix, String zipcode) throws CustomException {
        if (streetNumber < 1) throw new CustomException("Streetnumber cannot be lower then 1");
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.suffix = suffix;
        this.zipcode = zipcode;
    }

    public Address(String streetName, int streetNumber, String zipcode)throws CustomException {
        if (streetNumber < 1) throw new CustomException("Streetnumber cannot be lower then 1");
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.zipcode = zipcode;
        this.suffix = null;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int newStreetNumber) throws CustomException {
        if (newStreetNumber < 1) throw new CustomException("Streetnumber cannot be lower then 1");
        this.streetNumber = newStreetNumber;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getAdress_id() {
        return adress_id;
    }
}
