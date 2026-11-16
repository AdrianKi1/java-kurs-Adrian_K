package org.example.product;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;

public class ShoppingCart {

    private final List<Product> products = new ArrayList<>();
    private final DiscountPolicy discountPolicy;

    public ShoppingCart(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    public void add(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            products.add(product);
        }
    }
    public void remove(String sku) {
        for (Product product : products) {
            if (product.getSku().equals(sku)) {
                products.remove(product);
                break;
            }
        }
    }
    public Money getTotal() {
        Money sum = Money.zero("PLN");

        for (Product product : products) {
            if (discountPolicy.appliesTo(product)) {
                sum = sum.add(discountPolicy.apply(product.getTotalPrice()));
            } else {
                sum = sum.add(product.getTotalPrice());
            }
        }
        return sum;
    }

    public String generateReceipt() {
        String receipt = "";

        receipt += "Polityka rabatowa: " + discountPolicy.getName() + "\n";

        for (Product product : products) {
            receipt += product.getLabel() + "\n";
        }

        receipt += ("Razem: " + getTotal());

        return receipt;
    }
    public List<Product> getSortedBy(Comparator<Product> comparator) {
        List<Product> copy = new ArrayList<>(products);
        copy.sort(comparator);
        return copy;
    }
}
