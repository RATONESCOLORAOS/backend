package com.ratones_colorados.ahorro_compras.services;

import com.ratones_colorados.ahorro_compras.dto.CartProductDto;
import com.ratones_colorados.ahorro_compras.dto.UpdateCartProductRequest;
import com.ratones_colorados.ahorro_compras.models.CartProduct;
import com.ratones_colorados.ahorro_compras.repository.CartProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
            product.setCategoria(dto.getCategoria());

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

    public CartProduct updateProduct(Long productId, UpdateCartProductRequest updateRequest) {
        Optional<CartProduct> optionalCartProduct = cartProductRepository.findById(productId);
        if (optionalCartProduct.isPresent()) {
            CartProduct cartProduct = optionalCartProduct.get();

            if (updateRequest.getProductName() != null && !updateRequest.getProductName().isEmpty()) {
                cartProduct.setProductName(updateRequest.getProductName());
            }

            if (updateRequest.getMarca() != null && !updateRequest.getMarca().isEmpty()) {
                cartProduct.setMarca(updateRequest.getMarca());
            }

            if (updateRequest.getQuantity() != null) {
                cartProduct.setQuantity(updateRequest.getQuantity());
            }

            if (updateRequest.getCategoria() != null && !updateRequest.getCategoria().isEmpty()) {
                cartProduct.setCategoria(updateRequest.getCategoria());
            }

            return cartProductRepository.save(cartProduct);
        }
        return null;
    }


    public void addProductToCart(Long cartId, String productName, String marca, int quantity, String categoria) {
        CartProduct cartProduct = new CartProduct();
        cartProduct.setCartId(cartId);
        cartProduct.setProductName(productName);
        cartProduct.setMarca(marca);
        cartProduct.setQuantity(quantity);
        cartProduct.setCategoria(categoria);
        cartProductRepository.save(cartProduct);
    }

    public void removeProductFromCart(Long cartId, Long productId) {
        Optional<CartProduct> productOpt = cartProductRepository.findById(productId);
        if (productOpt.isPresent()) {
            CartProduct product = productOpt.get();
            if (product.getCartId().equals(cartId)) {
                cartProductRepository.delete(product);
            }
        }
    }
}