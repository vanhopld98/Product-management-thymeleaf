package com.codegym.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductForm {
    private int id;
    private String name;
    private double price;
    private String color;
    private MultipartFile image;

    public ProductForm() {
    }

    public ProductForm(int id, String name, double price, String color, MultipartFile image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}

