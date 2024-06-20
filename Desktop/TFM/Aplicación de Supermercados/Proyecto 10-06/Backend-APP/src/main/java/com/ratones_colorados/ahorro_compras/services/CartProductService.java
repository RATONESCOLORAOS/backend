package com.ratones_colorados.ahorro_compras.services;

import com.ratones_colorados.ahorro_compras.dto.CartProductDto;
import com.ratones_colorados.ahorro_compras.models.CartProduct;
import com.ratones_colorados.ahorro_compras.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartProductService {

    @Autowired
    private CartProductRepository cartProductRepository;

    public List<CartProduct> saveProducts(List<CartProductDto> productDtos) {
        List<CartProduct> cartProducts = productDtos.stream().map(dto -> {
            CartProduct product = new CartProduct();
            product.setCartId(dto.getCartId());
            product.setProductName(dto.getProductName());
            product.setMarca(dto.getMarca());
            product.setQuantity(dto.getQuantity());
            product.setCategoria(dto.getCategoria()); // Установка категории

            return product;
        }).collect(Collectors.toList());

        return cartProductRepository.saveAll(cartProducts);
    }

    public List<CartProduct> getProductsByCartId(Long cartId) {
        return cartProductRepository.findByCartId(cartId);
    }

    public void deleteProductById(Long id) {
        cartProductRepository.deleteById(id);
    }
}
