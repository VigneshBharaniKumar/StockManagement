package com.StockManagement.repository;

import java.util.List;

import com.StockManagement.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.StockManagement.model.Purchase;
import org.springframework.data.jpa.repository.Query;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Page<Purchase> findByProductName(String productName, Pageable pageable);

    @Query(value = "SELECT * FROM purchase WHERE product_name LIKE %?1% OR category LIKE %?1% OR seller LIKE %?1% OR purchase_date LIKE %?1%",
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM purchase WHERE product_name LIKE %?1% OR category LIKE %?1% OR seller LIKE %?1% OR purchase_date LIKE %?1%")
    Page<Purchase> search(String keyword, Pageable pageable);
}