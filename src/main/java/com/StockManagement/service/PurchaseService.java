package com.StockManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.StockManagement.model.Purchase;
import com.StockManagement.repository.PurchaseRepository;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository repository;

    public Purchase savePurchasedItem(Purchase purchasedItem){
        return repository.save(purchasedItem);
    }

    public List<Purchase> savePurchasedItems(List<Purchase> purchasedItem){
        return repository.saveAll(purchasedItem);
    }

    public Purchase getPurchasedItemById(Long id){
        return repository.findById(id).orElse(null);
    }

    public List<Purchase> getPurchasedItem(){
        return repository.findAll();
    }

    public List<Purchase> getPurchasedItemByName(String name){
        return repository.findByName(name);
    }

    public String deletePurchasedItem(Long id){
        repository.deleteById(id);
        return "Product " + id + " deleted successfully"; 
    }

    public Purchase updateProduct(Purchase purchasedItem){
        Purchase purchasedProduct = repository.findById(purchasedItem.getId()).orElse(null);
        purchasedProduct.setProduct_name(purchasedItem.getProduct_name());
        purchasedProduct.setCategory(purchasedItem.getCategory());
        purchasedProduct.setQuantity(purchasedItem.getQuantity());
        purchasedProduct.setPurchase_date(purchasedItem.getPurchase_date());
        purchasedProduct.setSeller(purchasedItem.getSeller());
        purchasedProduct.setPurchase_amount(purchasedItem.getPurchase_amount());
        purchasedProduct.setPurchase_price(purchasedItem.getPurchase_price());
        return repository.save(purchasedProduct);
    }  
}
