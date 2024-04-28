package com.MorningMart.controller;

import com.MorningMart.entity.Product;
import com.MorningMart.service.CartService;
import com.MorningMart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int  id, Model model) {
        productService.deleteImageById(id);
        productService.delete(id);
        return "redirect:/product";
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam("id") int id,
                                @RequestParam("name") String name,
                                @RequestParam("price") double price,
                                @RequestParam("newImage") MultipartFile file,
                                @RequestParam("oldImage") String oldImage, Model model ) throws IOException
    {
        System.out.println(name);
        System.out.println(price);
        System.out.println(file);
        System.out.println(oldImage);

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);

        final long micrometer = java.time.Instant.now().toEpochMilli();

        if ( file !=null && !file.isEmpty()) {
            productService.delete(id);
            String folder = "src/main/resources/static/images/products/";
            byte[] bytes = file.getBytes();
            String fileName = micrometer  + "_" + ".jpg";
            Path path = Paths.get(folder + fileName);
            Files.write(path, bytes);
            product.setImage(fileName);
        } else {
            product.setImage(oldImage);
        }

        productService.save(product);


        return "redirect:/product";
    }


    @GetMapping
    public String product(Model model){
        model.addAttribute("titel", "MorningMart | PRODUCT");
        model.addAttribute("page", "/product");

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        Long count = cartService.countCart();
        model.addAttribute("count", count);

        return "product";
    }

    @PostMapping("/add")
    public String addProduct(@RequestParam("name") String name,
                             @RequestParam("price") double price,
                             @RequestParam("image") MultipartFile file, Model model ) throws IOException
    {

        Product product = new Product();

        product.setName(name);
        product.setPrice(price);
        product.setQuantity(1);

        final long micrometer = java.time.Instant.now().toEpochMilli();

        String folder = "src/main/resources/static/images/products/";

        byte[] bytes = file.getBytes();

        String fileName = micrometer  + "_" + ".jpg";

        Path path = Paths.get(folder + fileName);

        Files.write(path, bytes);

        product.setImage(fileName);

        productService.save(product);

        return "redirect:/product";
    }

}
