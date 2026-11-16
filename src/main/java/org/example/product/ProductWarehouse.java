package org.example.product;

import java.util.Optional;

public class ProductWarehouse extends Warehouse<Product> {
    public Optional<Product> findCheapest() {
        Product cheapest = null;

        for (Product product : findAll()) {
            if (cheapest == null || product.getNetPrice().amount().compareTo(cheapest.getNetPrice().amount()) < 0) {
                cheapest = product;
            }
        }
        return Optional.ofNullable(cheapest);
    }
}
