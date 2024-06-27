package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

@Data
public class CartProductDto {

    private Long cartId;

    private String productName;

    private String marca;

    private Integer quantity;

    private String categoria;
}
