package com.MorningMart.service;

import com.MorningMart.entity.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private List<Product> productList = new ArrayList<Product>();

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public long countCart(){
        return productList.stream().count();
    }

    public double calculateTotalPrice() {
        double totalPrice = 0;
        for (Product item : productList) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }

    public  double calculatetotalAmount() {
        double totalAmount = 0;
        for (Product item : productList) {
            totalAmount += item.getPrice() ;
        }
        return totalAmount;
    }

    public void cearItem(){
        productList.clear();
    }

}
