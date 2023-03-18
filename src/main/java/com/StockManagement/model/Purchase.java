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
    private Long productId;
    @Column(nullable = false, length = 25)
    private String productName;
    @Column(nullable = false)
    private Date purchaseDate;
    private String category;
    @Column(nullable = false)
    private int quantity;
    private String seller;
    @Column(nullable = false)
    private int purchaseAmount;
    @Column(nullable = false)
    private int purchasePrice;

    public Purchase() {}

    public Purchase(Long id, Long productId, String productName, Date purchaseDate, String category, int quantity,
            String seller, int purchaseAmount, int purchasePrice) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.purchaseDate = purchaseDate;
        this.category = category;
        this.quantity = quantity;
        this.seller = seller;
        this.purchaseAmount = purchaseAmount;
        this.purchasePrice = purchasePrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public int getPurchaseAmount() {
        return purchaseAmount;
    }

    public void setPurchaseAmount(int purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(int purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    
}
