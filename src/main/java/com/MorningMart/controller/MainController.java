package com.MorningMart.controller;

import com.MorningMart.entity.Product;
import com.MorningMart.service.CartService;
import com.MorningMart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/")
    public String home( Model model) {

        model.addAttribute("titel", "MorningMart | TAPE");

        model.addAttribute("page", "/home");

        List<Product> products = productService.findAll();

        model.addAttribute("products", products);

        Long count = cartService.countCart();
        model.addAttribute("count", count);

        return "index";
    }


}
