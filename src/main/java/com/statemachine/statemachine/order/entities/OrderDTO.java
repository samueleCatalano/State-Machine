package com.statemachine.statemachine.order.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.statemachine.statemachine.user.entities.User;

import javax.persistence.ManyToOne;

public class OrderDTO extends Order {

    private String description;

    private String address;
    private String number;
    private String city;
    private String zipCode;
    private String state;
    private Long restaurant;
    private double totalPrice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getRestaurantDTO() {
        return restaurant;
    }

    public void setRestaurant(long restaurant) {
        this.restaurant = restaurant;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
