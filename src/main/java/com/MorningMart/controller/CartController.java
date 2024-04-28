package com.MorningMart.controller;

import com.MorningMart.entity.Product;
import com.MorningMart.service.CartService;
import com.MorningMart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model) {
        List<Product> productList = cartService.getProductList();
        model.addAttribute("productList", productList);

        Double total = 0.0;
        for (Product product : productList) {
            total += product.getPrice();
        }
        model.addAttribute("total", total);

        Long count = cartService.countCart();
        model.addAttribute("count", count);

        return "cart";
    }

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id) {
        boolean found = false;

        for (Product p : cartService.getProductList()) {

            if (p.getId() == id ) {

                p.setQuantity(p.getQuantity() + 1);

                p.setPrice(p.getPrice() * p.getQuantity());

                found = true;

                break;
            }
        }
        if (!found) {
            cartService.addProduct(productService.findById(id));
        }
//
        return "redirect:/";
    }

}
