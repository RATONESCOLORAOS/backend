package com.ratones_colorados.ahorro_compras.services;

import com.ratones_colorados.ahorro_compras.dto.CartDto;
import com.ratones_colorados.ahorro_compras.dto.UpdateCartRequest;
import com.ratones_colorados.ahorro_compras.dto.UpdateCartProductRequest;
import com.ratones_colorados.ahorro_compras.models.Cart;
import com.ratones_colorados.ahorro_compras.models.CartProduct;
import com.ratones_colorados.ahorro_compras.models.User;
import com.ratones_colorados.ahorro_compras.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductService cartProductService;

    @Autowired
    private SupermarketProductService supermarketProductService;

    public Cart saveCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public List<Cart> findCartsByUser(User user) {
        return cartRepository.findByUser(user);
    }

    public void deleteCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public List<CartDto> getCartsWithTotalPrices(User user) {
        List<Cart> carts = findCartsByUser(user);
        List<CartDto> cartDtos = new ArrayList<>();
        for (Cart cart : carts) {
            List<CartProduct> cartProducts = cartProductService.getProductsByCartId(cart.getId());
            double diaTotalPrice = getTotalPriceForCart(cartProducts, "DIA");
            double mercadonaTotalPrice = getTotalPriceForCart(cartProducts, "Mercadona");
            cartDtos.add(new CartDto(cart, diaTotalPrice, mercadonaTotalPrice));
        }
        return cartDtos;
    }

    public Map<String, Object> calculateTotalPriceForCart(Long cartId) {
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

        List<Map<String, Object>> diaProducts = (List<Map<String, Object>>) diaResult.get("detailedProducts");
        List<Map<String, Object>> mercadonaProducts = (List<Map<String, Object>>) mercadonaResult.get("detailedProducts");

        Map<String, Object> result = new HashMap<>();
        result.put("diaTotalPrice", diaTotalPrice);
        result.put("mercadonaTotalPrice", mercadonaTotalPrice);
        result.put("diaProducts", diaProducts);
        result.put("mercadonaProducts", mercadonaProducts);

        return result;
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

    public Cart updateCart(Long cartId, UpdateCartRequest updateRequest) {
        Optional<Cart> optionalCart = cartRepository.findById(cartId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();
            if (updateRequest.getCartName() != null && !updateRequest.getCartName().isEmpty()) {
                cart.setName(updateRequest.getCartName());
            }

            if (updateRequest.getProductsToAdd() != null) {
                for (UpdateCartProductRequest productRequest : updateRequest.getProductsToAdd()) {
                    cartProductService.addProductToCart(
                            cart.getId(),
                            productRequest.getProductName(),
                            productRequest.getMarca(),
                            productRequest.getQuantity(),
                            productRequest.getCategoria()
                    );
                }
            }

            if (updateRequest.getProductsToRemove() != null) {
                for (Long productId : updateRequest.getProductsToRemove()) {
                    cartProductService.removeProductFromCart(cart.getId(), productId);
                }
            }

            return cartRepository.save(cart);
        }
        return null;
    }
}
