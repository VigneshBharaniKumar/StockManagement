package com.StockManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

@Entity
@Table(name = "products")
public class Product {

    @Id   
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    private String category;
    private String description;
    private float selling_price;
    private int in_stock;
    private boolean active;

    public Product() {
    }

    public Product(String name, String category, String description, float selling_price, int in_stock,
            boolean active) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.selling_price = selling_price;
        this.in_stock = in_stock;
        this.active = active;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public float getSelling_price() {
        return selling_price;
    }
    public void setSelling_price(float selling_price) {
        this.selling_price = selling_price;
    }
    public int getIn_stock() {
        return in_stock;
    }
    public void setIn_stock(int in_stock) {
        this.in_stock = in_stock;
    }
    public boolean getActive() {
        return active;
    }
    public void setActive(boolean active) {
        this.active = active;
    }

}
