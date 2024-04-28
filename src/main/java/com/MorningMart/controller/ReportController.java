package com.MorningMart.controller;

import com.MorningMart.entity.Order;
import com.MorningMart.service.CartService;
import com.MorningMart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CartService cartService;

   @GetMapping
    public String report(Model model) {
       model.addAttribute("titel", "MorningMart | REPORT");
       model.addAttribute("page", "/REPORT");

       List<Order> orders = orderService.findAll();
       model.addAttribute("orders", orders);

       Long count = cartService.countCart();
       model.addAttribute("count", count);

       return "report";
   }
}