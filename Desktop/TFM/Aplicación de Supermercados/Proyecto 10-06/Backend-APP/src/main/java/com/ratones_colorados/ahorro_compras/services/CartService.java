package com.ratones_colorados.ahorro_compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratones_colorados.ahorro_compras.models.Cart;
import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.repository.CartRepository;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> findCartsByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
