package org.example.product;

import java.util.ArrayList;
import java.util.List;

public class Warehouse {
    private final List<Product> products = new ArrayList<>();

    public void add(Product product) {
        products.add(product);
    }
    public int count() {
        return products.size();
    }
    public Product findBySku(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                return product;
            }
        }
        return null;
    }
    public List<Product> getAll() {
        return new ArrayList<>(products);
    }
    public double getTotalValue() {
        double sum = 0;

        for (Product product : products) {
            sum += product.getTotalPrice();
        }
        return sum;
    }
}
