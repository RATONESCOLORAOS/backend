package com.ratones_colorados.ahorro_compras.controller;

import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.services.UserServicesImpl;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserServicesImpl userServices;

    @GetMapping("/users")
    public List<User> obtenerUsers() {
        return userServices.obtenerTodo();
    }

    @PostMapping("/guardar")
    public ResponseEntity<User> guardarUser(@RequestBody User user) {
        User newUser = userServices.guardar(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
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

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userServices.findByEmailAndPassword(email, password);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}
