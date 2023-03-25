package com.StockManagement.controller;

import com.StockManagement.model.Product;
import com.StockManagement.service.ProductService;
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

import com.StockManagement.model.Purchase;
import com.StockManagement.service.PurchaseService;

import java.util.List;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ProductService productService;

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
        int pageSize = 10;
        Page<Purchase> page = purchaseService.findPaginated(pageNo, pageSize);
        List<Purchase> listPurchase = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listPurchasedItems", listPurchase);
        return "purchase_list.html";
    }

    @GetMapping("/search")
    public String search(@Param("keyword") String keyword, Model model) {
        return searchByPage(model, keyword , 1);
    }

    @GetMapping("/search/{keyword}/page/{pageNum}")
    public String searchByPage(Model model, @PathVariable(name = "keyword") String keyword, @PathVariable(name = "pageNum") int pageNum) {
        if (keyword.isEmpty()) {
            return "redirect:/purchase";
        } else {
            Page<Purchase> result = purchaseService.search(keyword, pageNum, 10);
            List<Purchase> listPurchase = result.getContent();
            model.addAttribute("totalPages", result.getTotalPages());
            model.addAttribute("totalItems", result.getTotalElements());
            model.addAttribute("currentPage", pageNum);
            model.addAttribute("listPurchasedItems", listPurchase);
            model.addAttribute("keyword", keyword);
            return "purchase_list.html";
        }
    }

    @GetMapping("/add")
    public String addProduct(Model model){
        Purchase purchase = new Purchase();
        model.addAttribute("purchasedItem", purchase);
        model.addAttribute("listProducts", productService.getProducts());
        return "purchase_add.html";
    }

    @GetMapping("/add/search")
    public String productSearch(@Param("keyword") String keyword, Model model) {
        Purchase purchase = new Purchase();
        model.addAttribute("purchasedItem", purchase);
        if (keyword.isEmpty()) {
            return "redirect:/purchase/add";
        } else {
            model.addAttribute("listProducts", productService.search(keyword, 1, 100));
        }
        return "purchase_add.html";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("purchasedItem") Purchase purchase){
//        purchase.setProductId((long) 1);
        System.out.println(purchase.toString());
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
