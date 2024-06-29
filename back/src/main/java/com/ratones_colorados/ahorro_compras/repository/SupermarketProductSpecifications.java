package com.ratones_colorados.ahorro_compras.repository;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.Predicate;
import com.ratones_colorados.ahorro_compras.models.SupermarketProduct;

public class SupermarketProductSpecifications {

    public static Specification<SupermarketProduct> productoContainsWords(String[] words) {
        return (root, query, cb) -> {
            Predicate[] predicates = new Predicate[words.length];
            for (int i = 0; i < words.length; i++) {
                predicates[i] = cb.like(root.get("producto"), "%" + words[i] + "%");
            }
            return cb.and(predicates);
        };
    }

    public static Specification<SupermarketProduct> categoriaEquals(String categoria) {
        return (root, query, cb) -> cb.equal(root.get("categoria"), categoria);
    }
}
