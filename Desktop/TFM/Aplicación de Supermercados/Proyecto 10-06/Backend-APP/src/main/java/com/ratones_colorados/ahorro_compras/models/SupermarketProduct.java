package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class SupermarketProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private String producto;
    private String marca;
    private String formato;
    private String cantidad;
    private double precio;
    private String supermercado;

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    private String categoria;

    // getters and setters


    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getSupermercado() {
        return supermercado;
    }

    public void setSupermercado(String supermercado) {
        this.supermercado = supermercado;
    }

    @Override
    public String toString() {
        return "SupermarketProduct{" +
                "producto='" + producto + '\'' +
                ", marca='" + marca + '\'' +
                ", formato='" + formato + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", precio=" + precio +
                ", supermercado='" + supermercado + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }

}
