package com.ratones_colorados.ahorro_compras.controller;

import com.ratones_colorados.ahorro_compras.dto.CartProductDto;
import com.ratones_colorados.ahorro_compras.dto.UpdateCartProductRequest;
import com.ratones_colorados.ahorro_compras.models.CartProduct;
import com.ratones_colorados.ahorro_compras.services.CartProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart-products")
public class CartProductController {

    @Autowired
    private CartProductService cartProductService;

    @PostMapping("/{cartId}/save")
    public ResponseEntity<List<CartProduct>> saveProducts(@PathVariable Long cartId, @RequestBody List<CartProductDto> products) {
        // Убедитесь, что каждый продукт имеет правильный cartId
        products.forEach(product -> product.setCartId(cartId));
        List<CartProduct> savedProducts = cartProductService.saveProducts(products);
        return ResponseEntity.ok(savedProducts);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartProduct>> getProductsByCartId(@PathVariable Long cartId) {
        List<CartProduct> products = cartProductService.getProductsByCartId(cartId);
        return ResponseEntity.ok(products);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        cartProductService.deleteProductById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/updateProduct/{productId}")
    public ResponseEntity<CartProduct> updateCartProduct(@PathVariable Long productId, @RequestBody UpdateCartProductRequest updateCartProductRequest) {
        CartProduct updatedProduct = cartProductService.updateProduct(productId, updateCartProductRequest);
        if (updatedProduct == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }
}


