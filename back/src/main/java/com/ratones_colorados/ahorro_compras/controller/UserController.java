package com.ratones_colorados.ahorro_compras.controller;

import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.services.AuthenticationService;
import com.ratones_colorados.ahorro_compras.services.JwtService;
import com.ratones_colorados.ahorro_compras.services.UserServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/users")
    public List<User> obtenerUsers() {
        return userServices.obtenerTodo();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> obtenerUserId(@PathVariable long id) {
        User userPorId = userServices.obtenerPorId(id);
        return ResponseEntity.ok(userPorId);
    }

    @PutMapping("user/{id}")
    public ResponseEntity<User> actualizar(@PathVariable long id, @RequestBody User user) {
        User userPorId = userServices.obtenerPorId(id);
        userPorId.setName(user.getName());
        userPorId.setEmail(user.getEmail());
        userPorId.setPassword(user.getPassword());

        User userActualizado = userServices.guardar(userPorId);
        return new ResponseEntity<>(userActualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarUser(@PathVariable long id) {
        userServices.eliminar(id);
        HashMap<String, Boolean> estadoUserEliminado = new HashMap<>();
        estadoUserEliminado.put("eliminado", true);
        return ResponseEntity.ok(estadoUserEliminado);
    }
}
