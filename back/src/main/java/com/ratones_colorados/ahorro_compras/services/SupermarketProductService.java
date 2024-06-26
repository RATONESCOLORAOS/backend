package com.ratones_colorados.ahorro_compras.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ratones_colorados.ahorro_compras.models.SupermarketProduct;
import com.ratones_colorados.ahorro_compras.repository.SupermarketProductRepository;

import java.util.*;

@Service
public class SupermarketProductService {

    @Autowired
    private SupermarketProductRepository supermarketProductRepository;

    public List<SupermarketProduct> searchProductByNameAndCategory(String productName, String categoria) {
        String productNamePrefix = productName.split(" ")[0];
        return supermarketProductRepository.findByProductoStartingWithAndCategoria(productNamePrefix, categoria);
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
