package com.example.demo.entities;

public class ShopCart {

    private String phone;
    private String username;
    private String menu;
    private double price;
    private int number;
    private String destination;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenu() {
        return menu;
    }

    public void setMenu(String menu) {
        this.menu = menu;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    @Override
    public String toString() {
        return "ShopCart{" +
                "phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                ", menu='" + menu + '\'' +
                ", price=" + price +
                ", number=" + number +
                ", destination='" + destination + '\'' +
                '}';
    }
}
