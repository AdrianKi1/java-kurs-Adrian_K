package org.example.product;

import java.util.Comparator;
import java.util.Optional;

public class ProductWarehouse extends Warehouse<Product> {

    public Optional<Product> findCheapest() {
        return findAll().stream().min(Comparator.comparing(product -> product.getNetPrice().amount()));
    }
}
