package com.StockManagement.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

import jakarta.persistence.Column;

@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long product_id;
    @Column(nullable = false, length = 25)
    private String product_name;
    @Column(nullable = false)
    private Date purchase_date;
    private String category;
    @Column(nullable = false)
    private int quantity;
    private String seller;
    @Column(nullable = false)
    private int purchase_amount;
    @Column(nullable = false)
    private int purchase_price;

    public Purchase() {}

    public Purchase(Long id, Long product_id, String product_name, Date purchase_date, String category, int quantity,
            String seller, int purchase_amount, int purchase_price) {
        this.id = id;
        this.product_id = product_id;
        this.product_name = product_name;
        this.purchase_date = purchase_date;
        this.category = category;
        this.quantity = quantity;
        this.seller = seller;
        this.purchase_amount = purchase_amount;
        this.purchase_price = purchase_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Date getPurchase_date() {
        return purchase_date;
    }

    public void setPurchase_date(Date purchase_date) {
        this.purchase_date = purchase_date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public int getPurchase_amount() {
        return purchase_amount;
    }

    public void setPurchase_amount(int purchase_amount) {
        this.purchase_amount = purchase_amount;
    }

    public int getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(int purchase_price) {
        this.purchase_price = purchase_price;
    }

    
}
