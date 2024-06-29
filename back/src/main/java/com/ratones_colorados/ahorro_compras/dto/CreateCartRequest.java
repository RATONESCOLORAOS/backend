package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

/**
 * Clase DTO para la solicitud de creación de un carrito de compras.
 * Utiliza Lombok para generar automáticamente getters y setters.
 */
@Data
public class CreateCartRequest {

    /**
     * ID del usuario que crea el carrito.
     */
    private int user_Id;

    /**
     * Nombre del carrito.
     */
    private String cart_name;

    // Los métodos getUser_Id y getCart_name son generados automáticamente por Lombok
}
