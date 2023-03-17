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

import com.StockManagement.model.Purchase;
import com.StockManagement.service.PurchaseService;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;

    @PostMapping("/addPurchase")
    public Purchase addPurchase(@RequestBody Purchase purchase){
        return purchaseService.savePurchasedItem(purchase);
    }
    
    @GetMapping("")
    public String listPurchasedItems(Model model){
    //    model.addAttribute("listProducts", purchaseService.getPurchasedItem());
    //    return "purchase_list.html";
        return findPaginated(1, model);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model){
        int pageSize = 2;
        Page<Purchase> page = purchaseService.findPaginated(pageNo, pageSize);
        List<Purchase> listProducts = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPurchasedItems", listProducts);
        return "purchase_list.html";
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchasedItem", purchase);
        return "purchase_add.html";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("purchasedItem") Purchase purchase){
        purchase.setProductId((long) 1);
        purchaseService.savePurchasedItem(purchase);
        return "redirect:/purchase";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable(value = "id") long id, Model model){
        Purchase purchase = purchaseService.getPurchasedItemById(id);
        model.addAttribute("purchaseUpdate", purchase);
        return "purchase_update.html";
    }

    @PostMapping("/update/save")
    public String saveUpdateProduct(@ModelAttribute("purchaseUpdate") Purchase purchase){
        purchaseService.updatePurchasedItem(purchase);
        return "redirect:/purchase";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable(value = "id") long id){
        purchaseService.deletePurchasedItem(id);
        return "redirect:/purchase";
    }
    
}
