package com.StockManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StockManagement.model.Product;
import com.StockManagement.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product saveProduct(Product product){
        return repository.save(product);
    }
    public List<Product> saveProducts(List<Product> products){
        return repository.saveAll(products);
    }

    public Product getProductById(Long id){
        return repository.findById(id).orElse(null);
    }
    public List<Product> getProducts(){
        return repository.findAll();
    }
    public List<Product> getProductByName(String name){
        return repository.findByName(name);
    }

    public String deleteProduct(Long id){
        repository.deleteById(id);
        return "Product " +id+ " deleted successfully"; 
    }

    public Product updateProduct(Product product){
        Product product1 = repository.findById(product.getId()).orElse(null);
        product1.setName(product.getName());
        product1.setCategory(product.getCategory());
        product1.setDescription(product.getDescription());
        product1.setSelling_price(product.getSelling_price());
        product1.setIn_stock(product.getIn_stock());
        product1.setActive(product.getActive());
        return repository.save(product1);
    }
    
}
