package org.example.product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShoppingCart {

    private final List<Product> products = new ArrayList<>();
    private final DiscountPolicy discountPolicy;

    public ShoppingCart(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    public void add(Product product) {
        products.add(product);
    }
    public void remove(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                products.remove(product);
                break;
            }
        }
    }
    public double getTotal() {
        double sum = 0;

        for (Product product : products) {
            if (discountPolicy.appliesTo(product)) {
                sum += discountPolicy.apply(product.getTotalPrice());
            } else {
                sum += product.getTotalPrice();
            }
        }
        return sum;
    }

    public String getReceipt() {
        String receipt = "";

        receipt += "Polityka rabatowa: " + discountPolicy.getName() + "\n";

        for (Product product : products) {
            receipt += product.getLabel() + "\n";
        }

        receipt += String.format("Razem: %.2f zł", getTotal());

        return receipt;
    }
}
