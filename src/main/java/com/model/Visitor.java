package com.model;

import com.util.exeptions.CustomException;
import com.util.password.PassWordGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

import static javax.persistence.EnumType.STRING;

@Entity(name = "visitor")
public class Visitor {

    @Id()
    @NotNull
    @Size(min = 3, max = 30)
    @Email
    private String email;

    @Size(min = 3, max = 30)
    @NotNull
    private String userName;

    @ElementCollection(targetClass = DeliveryOption.class)
//    @JoinTable(name = "delivery_options", joinColumns = @JoinColumn(name = "email"))
    @Enumerated(STRING)
    @NotNull
    private Set<DeliveryOption> deliveryOptions;

    @Embedded
    private Address address;

    private String password;

    public Visitor() {
    }

    public Visitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String suffix, String zipcode) throws CustomException {

        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(email)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = email;
        this.deliveryOptions = deliveryOptions;
        this.address = new Address(streetName, streetNumber, suffix, zipcode);
        this.password = PassWordGenerator.generatePassword(12);
    }

    public Visitor(String userName, String email, Set<DeliveryOption> deliveryOptions, String streetName, int streetNumber, String zipcode) throws CustomException {

        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (!emailIsValid(email)) throw new CustomException("String email is invalid");
        this.userName = userName;
        this.email = email;
        this.deliveryOptions = deliveryOptions;
        this.address = new Address(streetName, streetNumber, zipcode);
        this.password = PassWordGenerator.generatePassword(12);
    }

    public Visitor(String userName, String email, Set<DeliveryOption> deliveryOptions) throws CustomException {

        if (deliveryOptions.isEmpty()) throw new CustomException("Must contain DeliveryOption");
        if (deliveryOptions.contains(DeliveryOption.PICKUPFROMHOME))
            throw new CustomException("For home pick an address must be given.");
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

    public Set<DeliveryOption> getDeliveryOptions() {
        return deliveryOptions;
    }

    public void addDeliveryOption(DeliveryOption deliveryOption) throws CustomException {
        if (deliveryOption.equals(DeliveryOption.PICKUPFROMHOME) && Objects.isNull(address))
            throw new CustomException("must contain address when choosing pickup from home");
        this.deliveryOptions.add(deliveryOption);
    }

    public void removeDeliveryOption(DeliveryOption deliveryOption) throws CustomException {

        if (deliveryOptions.size() <= 1) throw new CustomException("must at least contain one delivery method");
        deliveryOptions.remove(deliveryOption);
    }

    private boolean emailIsValid(String email) {
        return email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
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