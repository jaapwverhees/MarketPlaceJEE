package com.model;

import com.util.password.PassWordGenerator;
import com.util.exeptions.CustomException;

import java.util.ArrayList;

public class RegistredVisitor {
    private String userName;
    private String email;
    private ArrayList<DeliveryOption> deliveryOptions;
    private String streetName;
    private int streetNumber;
    private String suffix;
    private String zipcode;
    private String password;

    public RegistredVisitor(String userName, String inputEmail, ArrayList<DeliveryOption> inputDeliveryOptions, String streetName, int inputStreetNumber, String suffix, String zipcode) throws CustomException {
        if (inputDeliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(inputEmail)) throw new CustomException("String email is invalid");
        if (inputStreetNumber < 1) throw new CustomException("Streetnumber cannot be lower then 1");
        this.userName = userName;
        this.email = inputEmail;
        this.deliveryOptions = inputDeliveryOptions;
        this.streetName = streetName;
        this.streetNumber = inputStreetNumber;
        this.suffix = suffix;
        this.zipcode = zipcode;
        //TODO when instantiated, the object wil contain an un-hashed password. this will be hashed when put into
        //TODO the database. it could be hashed here, but then the Mailservice should also be called here, with is
        //TODO probably not desirable.
        this.password = PassWordGenerator.generatePassword(12);
    }

    public RegistredVisitor(String userName, String inputEmail, ArrayList<DeliveryOption> inputDeliveryOptions, String streetName, int inputStreetNumber, String zipcode) throws CustomException {
        if (inputDeliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(inputEmail)) throw new CustomException("String email is invalid");
        if (inputStreetNumber < 1) throw new CustomException("Streetnumber cannot be lower then 1");
        this.userName = userName;
        this.email = inputEmail;
        this.deliveryOptions = inputDeliveryOptions;
        this.streetName = streetName;
        this.streetNumber = inputStreetNumber;
        this.zipcode = zipcode;
        this.password = PassWordGenerator.generatePassword(12);
    }

    public RegistredVisitor(String userName, String inputEmail, ArrayList<DeliveryOption> inputDeliveryOptions) throws CustomException {
        if (inputDeliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(inputEmail)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = inputEmail;
        this.deliveryOptions = inputDeliveryOptions;
        this.password = PassWordGenerator.generatePassword(12);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String newEmail) throws CustomException {
        if (!emailIsValid(newEmail)) throw new CustomException("String email is invalid");
        else {
            this.email = newEmail;
        }
    }

    public ArrayList<DeliveryOption> getDeliveryOptions() {
        return deliveryOptions;
    }

    public void setDeliveryOption(DeliveryOption deliveryOption) throws CustomException {
        if (deliveryOption.equals(DeliveryOption.PICKUPFROMHOME) && (streetName == null || streetNumber == 0 || zipcode == null))
            throw new CustomException("must contain address when choosing pickup from home");
        for (DeliveryOption option : deliveryOptions) {
            if (option.equals(deliveryOption)) throw new CustomException("already has this delivery option");
        }
        deliveryOptions.add(deliveryOption);
    }

    public void RemoveDeliveryOption(DeliveryOption deliveryOption) throws CustomException {
        if (deliveryOptions.size() <= 1) throw new CustomException("must at least contain one delivery method");
        deliveryOptions.remove(deliveryOption);
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

    private boolean emailIsValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
