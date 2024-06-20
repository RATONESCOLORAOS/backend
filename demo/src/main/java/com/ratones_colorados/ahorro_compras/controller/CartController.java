package com.ratones_colorados.ahorro_compras.controller;
import com.ratones_colorados.ahorro_compras.dto.CartDto;
import com.ratones_colorados.ahorro_compras.dto.CreateCartRequest;
import com.ratones_colorados.ahorro_compras.dto.UpdateCartRequest;
import com.ratones_colorados.ahorro_compras.models.Cart;
import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.services.CartProductService;
import com.ratones_colorados.ahorro_compras.services.CartService;
import com.ratones_colorados.ahorro_compras.services.IUserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private CartProductService cartProductService;

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartRequest createCartRequest) {
        User user = userServices.obtenerPorId(createCartRequest.getUser_Id());
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart newCart = new Cart(createCartRequest.getCart_name(), user);
        Cart savedCart = cartService.saveCart(newCart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDto>> getUserCarts(@PathVariable Long userId) {
        User user = userServices.obtenerPorId(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<CartDto> cartDtos = cartService.getCartsWithTotalPrices(user);
        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/totalPrice/{cartId}")
    public ResponseEntity<Map<String, Object>> getTotalPriceForCart(@PathVariable Long cartId) {
        Map<String, Object> result = cartService.calculateTotalPriceForCart(cartId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable Long cartId, @RequestBody UpdateCartRequest updateCartRequest) {
        Cart updatedCart = cartService.updateCart(cartId, updateCartRequest);
        if (updatedCart == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

}
