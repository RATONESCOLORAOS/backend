package com.ratones_colorados.ahorro_compras.dto;

import com.ratones_colorados.ahorro_compras.models.Cart;

public class CartDto {
    private Cart cart;
    private double diaTotalPrice;
    private double mercadonaTotalPrice;

    public CartDto(Cart cart, double diaTotalPrice, double mercadonaTotalPrice) {
        this.cart = cart;
        this.diaTotalPrice = diaTotalPrice;
        this.mercadonaTotalPrice = mercadonaTotalPrice;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public double getDiaTotalPrice() {
        return diaTotalPrice;
    }

    public void setDiaTotalPrice(double diaTotalPrice) {
        this.diaTotalPrice = diaTotalPrice;
    }

    public double getMercadonaTotalPrice() {
        return mercadonaTotalPrice;
    }

    public void setMercadonaTotalPrice(double mercadonaTotalPrice) {
        this.mercadonaTotalPrice = mercadonaTotalPrice;
    }
}
