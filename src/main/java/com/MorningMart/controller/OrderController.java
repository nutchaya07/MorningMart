package com.MorningMart.controller;

import com.MorningMart.entity.Order;
import com.MorningMart.service.CartService;
import com.MorningMart.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    private final CartService cartService;

    public OrderController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/checkout")
    public String checkout(
                            @RequestParam("name") String name,
                            @RequestParam("quantity") Integer quantity,
                            @RequestParam("price") Double price,
                            @RequestParam("total") Double total, Model model) {



        if ( name == null || quantity == null || price == null || total == null ) {
            return "redirect:/";
        }

        long micrometer = java.time.Instant.now().toEpochMilli();
        int numInvoice = (int) micrometer;

        Order order = new Order();
        order.setTex(String.valueOf(numInvoice));
        order.setTotalPrice(price);
        order.setTotalPrice(total);

        orderService.save(order);
        cartService.cearItem();

//        List<Product> products = cartService.getProductList();
        Long count = cartService.countCart();
        model.addAttribute("count", count);

        return "redirect:/";
    }
}
