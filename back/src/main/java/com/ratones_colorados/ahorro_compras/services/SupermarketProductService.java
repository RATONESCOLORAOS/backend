package com.ratones_colorados.ahorro_compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.ratones_colorados.ahorro_compras.models.SupermarketProduct;
import com.ratones_colorados.ahorro_compras.repository.SupermarketProductRepository;
import com.ratones_colorados.ahorro_compras.repository.SupermarketProductSpecifications;

import java.util.*;

@Service
public class SupermarketProductService {

    private final SupermarketProductRepository supermarketProductRepository;

    @Autowired
    public SupermarketProductService(SupermarketProductRepository supermarketProductRepository) {
        this.supermarketProductRepository = supermarketProductRepository;
    }

    public List<SupermarketProduct> searchProductByNameAndCategory(String productName, String categoria) {
        String[] words = productName.split(" ");
        Specification<SupermarketProduct> spec = Specification.where(SupermarketProductSpecifications.categoriaEquals(categoria))
                .and(SupermarketProductSpecifications.productoContainsWords(words));
        return supermarketProductRepository.findAll(spec);
    }

    public Map<String, Object> getTotalPriceForCart(List<String> productNames, List<Integer> quantities, List<String> categories, String supermarket) {
        double totalPrice = 0.0;
        List<Map<String, Object>> detailedProducts = new ArrayList<>();

        for (int i = 0; i < productNames.size(); i++) {
            String productName = productNames.get(i);
            int quantity = quantities.get(i);
            String category = categories.get(i);
            List<SupermarketProduct> products = searchProductByNameAndCategory(productName, category);
            Optional<SupermarketProduct> minPriceProduct = products.stream()
                    .filter(product -> product.getSupermercado().equalsIgnoreCase(supermarket))
                    .min(Comparator.comparingDouble(SupermarketProduct::getPrecio));

            if (minPriceProduct.isPresent()) {
                SupermarketProduct product = minPriceProduct.get();
                totalPrice += product.getPrecio() * quantity;
                Map<String, Object> productInfo = new HashMap<>();
                productInfo.put("product", product);
                productInfo.put("quantity", quantity);
                productInfo.put("totalPriceForProduct", product.getPrecio() * quantity);
                detailedProducts.add(productInfo);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("totalPrice", totalPrice);
        result.put("detailedProducts", detailedProducts);

        return result;
    }
}
