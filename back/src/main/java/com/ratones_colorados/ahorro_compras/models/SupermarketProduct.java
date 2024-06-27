package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupermarketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private String producto;

    private String marca;

    private String formato;

    private String cantidad;

    private double precio;

    private String supermercado;

    private String categoria;
}
