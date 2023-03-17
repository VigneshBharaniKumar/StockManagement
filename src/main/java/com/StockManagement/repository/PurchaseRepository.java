package com.StockManagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StockManagement.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findByName(String product_name);
}
