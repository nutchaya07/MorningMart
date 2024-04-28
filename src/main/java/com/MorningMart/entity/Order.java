package com.MorningMart.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orderdetail")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String tex;
    private Double totalPrice;

    public Order() {
        super();
    }

    public Order(String tex, Double totalPrice) {
        this.tex = tex;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTex() {
        return tex;
    }

    public void setTex(String tex) {
        this.tex = tex;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
