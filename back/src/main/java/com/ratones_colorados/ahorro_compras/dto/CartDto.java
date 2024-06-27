package com.ratones_colorados.ahorro_compras.dto;

import com.ratones_colorados.ahorro_compras.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {

    private Cart cart;

    private double diaTotalPrice;

    private double mercadonaTotalPrice;
}
