package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

@Data
public class CreateCartRequest {

    private int user_Id;

    private String cart_name;

}
