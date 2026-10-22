package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ProductClass {
    private final String sku;
    private final String name;
    private double netPrice;
    private final List<String> tags;

    public ProductClass(String sku, String name, double netPrice, List<String> tags) {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid sku");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (netPrice <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        this.sku = sku.trim().toUpperCase();
        this.name = name.trim();
        this.netPrice = netPrice;
        this.tags = tags;
    }
    public String getSku() {
        return sku;
    }
    public String getName() {
        return name;
    }
    public double getNetPrice() {
        return netPrice;
    }
    public void raisePriceBy(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException();
        }
        netPrice = netPrice + (netPrice * percent / 100);
    }
    public void applyDiscount(double percent) {
        if (percent <= 0 || percent >= 100) {
            throw new IllegalArgumentException();
        }
        double newPrice = netPrice - (netPrice * percent / 100);

        if(newPrice < 0.01) {
            throw new IllegalArgumentException();
        }
        netPrice = newPrice;

    }
    public void addTag(String tag) {
        if (tag == null || tag.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }
        tags.add(tag.trim().toLowerCase());
    }
    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    @Override
    public String toString() {
        return sku + " " + name + " - " + netPrice +
                " zł [" + String.join(", ", tags) + "]";
    }

    public static void main(String[] args) {
        ProductClass productClass = new ProductClass(
                "elec-001",
                "Laptop",
                4000,
                new ArrayList<>(List.of("elektronika"))
        );
        System.out.println(productClass);

        productClass.applyDiscount(10);
        System.out.println("Po rabacie: " + productClass.getNetPrice());

        productClass.raisePriceBy(10);
        System.out.println("Po podwyżce: " + productClass.getNetPrice());

        try {
            productClass.getTags().add("promocja");
        } catch (Exception e) {
            System.out.println("Nie można modyfikować listy tagów");
        }

        try {
            new ProductClass("", "Laptop", 1000, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            System.out.println("Pusty SKU odrzucony");
        }

        try {
            new ProductClass("ABC", "", 1000, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            System.out.println("Ujemna cena odrzucona");
        }
    }
}