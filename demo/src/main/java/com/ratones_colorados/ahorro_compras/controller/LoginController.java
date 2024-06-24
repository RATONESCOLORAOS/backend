package com.ratones_colorados.ahorro_compras.controller;

import com.ratones_colorados.ahorro_compras.dto.User;
import com.ratones_colorados.ahorro_compras.security.JWTAuthtenticationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @PostMapping("login")
    public User login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass) {

        /**
         * En el ejemplo no se realiza la correcta validación del usuario
         */

        String token = jwtAuthtenticationConfig.getJWTToken(username);
        return new User(username, encryptedPass, token);

    }

}
