
package com.model;

import com.util.password.PassWordGenerator;
import com.util.exeptions.CustomException;

import java.util.ArrayList;
import java.util.Objects;

public class RegistredVisitor {
    private String userName;
    private String email;
    private ArrayList<DeliveryOption> deliveryOptions;
    private Address address;
    private String password;

    public RegistredVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) throws CustomException {
        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(email)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = email;
        this.deliveryOptions = deliveryOptions;
        this.address = new Address(streetName, streetNumber, suffix,zipcode);
        this.password = PassWordGenerator.generatePassword(12);
    }
    //TODO Arraylist should we something that only contains uniques, set?
    public RegistredVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) throws CustomException {
        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(email)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = email;
        this.deliveryOptions = deliveryOptions;
        this.address = new Address(streetName, streetNumber,zipcode);
        this.password = PassWordGenerator.generatePassword(12);
    }

    public RegistredVisitor(String userName, String email, ArrayList<DeliveryOption> deliveryOptions) throws CustomException {
        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(email)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = email;
        this.deliveryOptions = deliveryOptions;
        this.address = null;
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
        if (deliveryOption.equals(DeliveryOption.PICKUPFROMHOME) && Objects.nonNull(address))
            throw new CustomException("must contain address when choosing pickup from home");
        for (DeliveryOption option : this.deliveryOptions) {
            if (option.equals(deliveryOption))
                throw new CustomException("already has this delivery option");
        }
        this.deliveryOptions.add(deliveryOption);
    }

    public void RemoveDeliveryOption(DeliveryOption deliveryOption) throws CustomException {
        if (deliveryOptions.size() <= 1) throw new CustomException("must at least contain one delivery method");
        deliveryOptions.remove(deliveryOption);
    }

    private boolean emailIsValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}