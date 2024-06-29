package com.ratones_colorados.ahorro_compras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ratones_colorados.ahorro_compras.models.SupermarketProduct;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

@Repository



public interface SupermarketProductRepository extends JpaRepository<SupermarketProduct, Long>, JpaSpecificationExecutor<SupermarketProduct> {
}

