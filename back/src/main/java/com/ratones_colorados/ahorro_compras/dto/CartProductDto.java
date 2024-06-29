package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

/**
 * Clase DTO para transferir datos de los productos en un carrito de compras.
 * Utiliza Lombok para generar automáticamente getters y setters.
 */
@Data
public class CartProductDto {
    /**
     * ID del carrito de compras.
     */
    private Long cartId;

    /**
     * Nombre del producto.
     */
    private String productName;

    /**
     * Marca del producto.
     */
    private String marca;

    /**
     * Cantidad del producto en el carrito.
     */
    private Integer quantity;

    /**
     * Categoría del producto.
     */
    private String categoria;
}
