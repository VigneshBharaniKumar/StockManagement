package com.StockManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
        Page<Product> page = service.findPaginated(pageNo, 10);
        List<Product> listProducts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listProducts", listProducts);
        model.addAttribute("keyword", "");
        return "products_list.html";
    }

    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        return searchByPage(model, keyword , 1);
    }

    @GetMapping("/search/{keyword}/page/{pageNum}")
    public String searchByPage(Model model, @PathVariable(name = "keyword") String keyword, @PathVariable(name = "pageNum") int pageNum) {
        if (keyword.isEmpty()) {
            return "redirect:/products";
        } else {
            Page<Product> result = service.search(keyword, pageNum, 10);
            List<Product> listProducts = result.getContent();
            model.addAttribute("totalPages", result.getTotalPages());
            model.addAttribute("totalItems", result.getTotalElements());
            model.addAttribute("currentPage", pageNum);
            model.addAttribute("listProducts", listProducts);
            model.addAttribute("keyword", keyword);
            return "products_list.html";
        }
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
