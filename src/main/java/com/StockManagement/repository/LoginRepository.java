package com.StockManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.StockManagement.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long>{
    Login findByUsernameAndPassword(String username, String password);
}
