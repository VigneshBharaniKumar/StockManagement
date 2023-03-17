package com.StockManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.StockManagement.model.Product;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product,Long>{

    List<Product> findByName(String name);

    @Query(value = "SELECT * FROM products WHERE MATCH(name, category, description) AGAINST (?1)",
            nativeQuery = true,
            countQuery = "SELECT COUNT(*) FROM products WHERE MATCH(name, category, description) AGAINST (?1)")
    public Page<Product> search(String keyword, Pageable pageable);

}
