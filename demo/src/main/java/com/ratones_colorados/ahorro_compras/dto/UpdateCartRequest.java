package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateCartRequest {
    private String cartName;
    private List<UpdateCartProductRequest> productsToAdd;
    private List<Long> productsToRemove;
}