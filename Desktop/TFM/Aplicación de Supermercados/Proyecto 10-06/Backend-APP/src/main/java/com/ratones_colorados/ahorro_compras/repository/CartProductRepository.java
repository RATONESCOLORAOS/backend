package com.ratones_colorados.ahorro_compras.repository;

import com.ratones_colorados.ahorro_compras.models.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByCartId(Long cartId);

    void deleteById(Long id);
}


