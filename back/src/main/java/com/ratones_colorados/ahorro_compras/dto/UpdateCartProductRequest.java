package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

/**
 * Clase DTO para actualizar productos en un carrito de compras.
 * Utiliza Lombok para generar automáticamente getters y setters.
 */
@Data
public class UpdateCartProductRequest {
    private String productName;
    private String marca;
    private Integer quantity;
    private String categoria;
}
