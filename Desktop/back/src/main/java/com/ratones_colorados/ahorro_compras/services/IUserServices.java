package com.ratones_colorados.ahorro_compras.services;

import java.util.List;
import com.ratones_colorados.ahorro_compras.models.User;

public interface IUserServices {
    List<User> obtenerTodo();
    User guardar(User user);
    User obtenerPorId(long id);
    void eliminar(long id);
    User findByEmailAndPassword(String email, String password);

}

