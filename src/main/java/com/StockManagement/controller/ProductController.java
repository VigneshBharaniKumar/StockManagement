package com.StockManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.StockManagement.model.Product;
import com.StockManagement.service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;
    
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return service.saveProduct(product);
    }
    
    @GetMapping("")
    public String listProducts(Model model){
//        model.addAttribute("listProducts", service.getProducts());
//        return "products_list.html";
        return findPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 15;
        Page<Product> page = service.findPaginated(pageNo, pageSize);
        List<Product> listProducts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        return "products_list.html";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        Product product = new Product();
        model.addAttribute("product", product);
        return "products_add.html";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product") Product product){
        product.setActive(true);
        service.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") long id, Model model){
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "products_update.html";
    }

    @PostMapping("/update/save")
    public String saveUpdateProduct(@ModelAttribute("product") Product product){
        service.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id){
        service.deleteProduct(id);
        return "redirect:/products";
    }
    
}
