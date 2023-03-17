package com.StockManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return repository.findByProductName(name);
    }

    public String deletePurchasedItem(Long id){
        repository.deleteById(id);
        return "Product " + id + " deleted successfully"; 
    }

    public Purchase updatePurchasedItem(Purchase purchasedItem){
        Purchase purchasedProduct = repository.findById(purchasedItem.getId()).orElse(null);
        purchasedProduct.setProductName(purchasedItem.getProductName());
        purchasedProduct.setCategory(purchasedItem.getCategory());
        purchasedProduct.setQuantity(purchasedItem.getQuantity());
        purchasedProduct.setPurchaseDate(purchasedItem.getPurchaseDate());
        purchasedProduct.setSeller(purchasedItem.getSeller());
        purchasedProduct.setPurchaseAmount(purchasedItem.getPurchaseAmount());
        purchasedProduct.setPurchasePrice(purchasedItem.getPurchasePrice());
        return repository.save(purchasedProduct);
    }  

    public Page<Purchase> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.repository.findAll(pageable);
    }
}
