package com.ratones_colorados.ahorro_compras.controller;

import com.ratones_colorados.ahorro_compras.dto.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ratones_colorados.ahorro_compras.dto.CreateCartRequest;
import com.ratones_colorados.ahorro_compras.models.Cart;
import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.services.CartService;
import com.ratones_colorados.ahorro_compras.services.IUserServices;
import com.ratones_colorados.ahorro_compras.services.CartProductService;
import com.ratones_colorados.ahorro_compras.services.SupermarketProductService;
import com.ratones_colorados.ahorro_compras.models.CartProduct;
import com.ratones_colorados.ahorro_compras.models.SupermarketProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private IUserServices userServices;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private SupermarketProductService supermarketProductService;

    @PostMapping("/create")
    public ResponseEntity<Cart> createCart(@RequestBody CreateCartRequest createCartRequest) {
        System.out.println("Received request to create cart with name: " + createCartRequest.getCart_name() + " for user ID: " + createCartRequest.getUser_Id());
        User user = userServices.obtenerPorId(createCartRequest.getUser_Id());
        if (user == null) {
            System.out.println("User not found with ID: " + createCartRequest.getUser_Id());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cart newCart = new Cart(createCartRequest.getCart_name(), user);
        Cart savedCart = cartService.saveCart(newCart);
        System.out.println("Saved cart: " + savedCart);
        return new ResponseEntity<>(savedCart, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDto>> getUserCarts(@PathVariable Long userId) {
        User user = userServices.obtenerPorId(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<Cart> carts = cartService.findCartsByUser(user);

        // Создаем список DTO для корзин
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts) {
            List<CartProduct> cartProducts = cartProductService.getProductsByCartId(cart.getId());
            double diaTotalPrice = getTotalPriceForCart(cartProducts, "DIA");
            double mercadonaTotalPrice = getTotalPriceForCart(cartProducts, "Mercadona");
            cartDtos.add(new CartDto(cart, diaTotalPrice, mercadonaTotalPrice));
        }

        return new ResponseEntity<>(cartDtos, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long cartId) {
        cartService.deleteCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/totalPrice/{cartId}")
    public ResponseEntity<Map<String, Object>> getTotalPriceForCart(@PathVariable Long cartId) {
        List<CartProduct> cartProducts = cartProductService.getProductsByCartId(cartId);
        List<String> productNames = cartProducts.stream()
                .map(CartProduct::getProductName)
                .collect(Collectors.toList());
        List<Integer> quantities = cartProducts.stream()
                .map(CartProduct::getQuantity)
                .collect(Collectors.toList());
        List<String> categories = cartProducts.stream()
                .map(CartProduct::getCategoria)
                .collect(Collectors.toList());

        Map<String, Object> diaResult = supermarketProductService.getTotalPriceForCart(productNames, quantities, categories, "DIA");
        Map<String, Object> mercadonaResult = supermarketProductService.getTotalPriceForCart(productNames, quantities, categories, "Mercadona");

        double diaTotalPrice = (double) diaResult.get("totalPrice");
        double mercadonaTotalPrice = (double) mercadonaResult.get("totalPrice");

        // Получаем информацию о продуктах для каждого магазина
        List<Map<String, Object>> diaProducts = (List<Map<String, Object>>) diaResult.get("detailedProducts");
        List<Map<String, Object>> mercadonaProducts = (List<Map<String, Object>>) mercadonaResult.get("detailedProducts");

        // Создаем HashMap для хранения результатов
        Map<String, Object> result = new HashMap<>();
        result.put("diaTotalPrice", diaTotalPrice);
        result.put("mercadonaTotalPrice", mercadonaTotalPrice);
        result.put("diaProducts", diaProducts); // Добавляем информацию о продуктах для DIA
        result.put("mercadonaProducts", mercadonaProducts); // Добавляем информацию о продуктах для Mercadona

        // Возвращаем результат в теле ответа с кодом статуса HttpStatus.OK
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    private double getTotalPriceForCart(List<CartProduct> cartProducts, String supermarket) {
        List<String> productNames = cartProducts.stream()
                .map(CartProduct::getProductName)
                .collect(Collectors.toList());
        List<Integer> quantities = cartProducts.stream()
                .map(CartProduct::getQuantity)
                .collect(Collectors.toList());
        List<String> categories = cartProducts.stream()
                .map(CartProduct::getCategoria)
                .collect(Collectors.toList());

        Map<String, Object> result = supermarketProductService.getTotalPriceForCart(productNames, quantities, categories, supermarket);
        return (double) result.get("totalPrice");
    }
}
