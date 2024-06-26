package com.ratones_colorados.ahorro_compras.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Clase que representa un usuario.
 * Implementa Serializable para permitir la serialización del objeto.
 * Utiliza Lombok para generar getters, setters, y otros métodos útiles.
 */
@Entity
@Data // Genera getters, setters, métodos toString, equals y hashCode
public class User implements Serializable, UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
