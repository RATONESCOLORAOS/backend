package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Clase que representa un carrito de compras.
 * Utiliza Lombok para generar automáticamente los métodos getters, setters, constructores y el método builder.
 */
@Entity(name = "user_carts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Identificador único del carrito.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * Nombre del carrito.
     */
    @Column(name = "cart_name", nullable = false)
    private String name;

    /**
     * Usuario al que pertenece el carrito.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Fecha y hora de creación del carrito.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Constructor que inicializa la fecha de creación.
     */
    public Cart(String name, User user) {
        this.name = name;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }
}
