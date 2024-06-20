package com.ratones_colorados.ahorro_compras.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.repository.UserRepository;

@Service
public class UserServicesImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> obtenerTodo() {
        return userRepository.findAll();
    }

    @Override
    public User guardar(User user) {
        return userRepository.save(user);
    }

    @Override
    public User obtenerPorId(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminar(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }


}
