package com.ratones_colorados.ahorro_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ratones_colorados.ahorro_compras.models.Cart;
import com.ratones_colorados.ahorro_compras.models.User;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}


