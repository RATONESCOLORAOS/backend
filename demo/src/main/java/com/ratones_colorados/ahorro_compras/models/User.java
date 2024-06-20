package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Clase que representa un usuario.
 * Implementa Serializable para permitir la serialización del objeto.
 * Utiliza Lombok para generar getters, setters, y otros métodos útiles.
 */
@Entity
@Data // Genera getters, setters, métodos toString, equals y hashCode
@NoArgsConstructor // Genera un constructor sin parámetros
public class User implements Serializable {

    // Identificador único para la serialización
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Campo de identificador único del usuario en la base de datos
    private long id;

    // Nombre del usuario
    private String name;

    // Correo electrónico del usuario
    private String email;

    // Contraseña del usuario
    private String password;

    // Lombok generará automáticamente los getters, setters y otros métodos, por lo que no es necesario escribirlos manualmente
}
