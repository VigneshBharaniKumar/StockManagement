package com.StockManagement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


@Controller
public class ProductController {
    
    @GetMapping("/")
    public String home() {
        return "home.html";
    }

}
