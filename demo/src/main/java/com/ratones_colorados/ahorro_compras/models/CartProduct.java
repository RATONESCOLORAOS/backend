package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Clase que representa la relación entre un carrito y un producto.
 * Utiliza Lombok para generar automáticamente los métodos getters, setters, constructores y el método builder.
 */
@Entity
@Table(name = "cart_products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartProduct {

    /**
     * Identificador único de la relación carrito-producto.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Identificador del carrito al que pertenece este producto.
     */
    @Column(name = "cart_id", nullable = false)
    private Long cartId;

    /**
     * Nombre del producto.
     */
    @Column(name = "product_name", nullable = false)
    private String productName;

    /**
     * Cantidad del producto en el carrito.
     */
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    /**
     * Marca del producto (puede ser nulo).
     */
    @Column(name = "marca", nullable = true)
    private String marca;

    /**
     * Categoría del producto (puede ser nulo).
     */
    @Column(name = "categoria", nullable = true)
    private String categoria;

    /**
     * Fecha y hora de creación del registro.
     * Se inicializa automáticamente al crear un nuevo registro.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}
