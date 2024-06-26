package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Clase que representa un producto de supermercado.
 * Utiliza Lombok para generar getters, setters, constructores, toString y otros métodos.
 */
@Entity
@Data // Genera getters, setters, métodos toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
@AllArgsConstructor // Genera un constructor con todos los campos como parámetros
public class SupermarketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    // Nombre del producto
    private String producto;

    // Marca del producto
    private String marca;

    // Formato del producto (por ejemplo, paquete)
    private String formato;

    // Cantidad del producto (por ejemplo, peso o volumen)
    private String cantidad;

    // Precio del producto
    private double precio;

    // Nombre del supermercado donde se vende el producto
    private String supermercado;

    // Categoría del producto
    private String categoria;
}
