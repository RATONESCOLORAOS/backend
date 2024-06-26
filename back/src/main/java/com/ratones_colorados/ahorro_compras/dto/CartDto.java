package com.ratones_colorados.ahorro_compras.dto;

import com.ratones_colorados.ahorro_compras.models.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Clase DTO para transferir datos de la entidad Cart junto con los precios totales
 * de los productos en dos supermercados diferentes: DIA y Mercadona.
 * Utiliza Lombok para generar automáticamente getters, setters, constructores,
 * y otros métodos comunes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartDto {
    /**
     * El objeto Cart que representa el carrito de compras.
     */
    private Cart cart;

    /**
     * El precio total de los productos en el carrito en el supermercado DIA.
     */
    private double diaTotalPrice;

    /**
     * El precio total de los productos en el carrito en el supermercado Mercadona.
     */
    private double mercadonaTotalPrice;
}
