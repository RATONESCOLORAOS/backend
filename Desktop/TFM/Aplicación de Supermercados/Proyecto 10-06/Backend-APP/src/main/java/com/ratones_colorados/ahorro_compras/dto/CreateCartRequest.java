package com.ratones_colorados.ahorro_compras.dto;

public class CreateCartRequest {

    private int user_Id;

    private String cart_name;

    public int getUser_Id() {
        return user_Id;
    }

    public String getCart_name() {
        return cart_name;
    }
}
