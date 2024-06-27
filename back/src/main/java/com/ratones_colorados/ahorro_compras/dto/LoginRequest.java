package com.ratones_colorados.ahorro_compras.dto;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;

    private String password;
}
