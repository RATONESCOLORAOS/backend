package com.ratones_colorados.ahorro_compras.controller;

import lombok.Data;

/**
 * Clase que representa una solicitud de inicio de sesión.
 * Utiliza Lombok para generar automáticamente getters, setters,
 * y otros métodos comunes.
 */
@Data
public class LoginRequest {
    /**
     * El email del usuario que intenta iniciar sesión.
     */
    private String email;

    /**
     * La contraseña del usuario que intenta iniciar sesión.
     */
    private String password;
}
