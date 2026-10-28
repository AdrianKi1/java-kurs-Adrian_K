package org.example.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProductMethodsObject {

    private static final double DEFAULT_VAT_RATE = 0.23;
    private static int createdCount;

    private final String sku;
    private final String name;
    private double netPrice;
    private final List<String> tags;

    private ProductMethodsObject(String sku, String name, double netPrice, List<String> tags) {

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
        this.tags = new ArrayList<>(tags);

        createdCount++;
    }

    public ProductMethodsObject(ProductMethodsObject other) {
        this(
                other.sku,
                other.name,
                other.netPrice,
                other.tags
        );
    }

    public static ProductMethodsObject of(String sku, String name, double netPrice) {
        return new ProductMethodsObject(
                sku,
                name,
                netPrice,
                new ArrayList<>()
        );
    }

    public static ProductMethodsObject fromCsvLine(String line) {

        try {
            String[] parts = line.split(";");

            if (parts.length != 3) {
                throw new IllegalArgumentException(line);
            }

            return ProductMethodsObject.of(
                    parts[0],
                    parts[1],
                    Double.parseDouble(parts[2])
            );

        } catch (Exception e) {
            throw new IllegalArgumentException(line);
        }
    }

    public static int getCreatedCount() {
        return createdCount;
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

    public double getGrossPrice() {
        return netPrice * (1 + DEFAULT_VAT_RATE);
    }

    public void raisePriceBy(double percent) {

        if (percent <= 0) {
            throw new IllegalArgumentException();
        }

        netPrice += netPrice * percent / 100;
    }

    public void applyDiscount(double percent) {

        if (percent <= 0 || percent >= 100) {
            throw new IllegalArgumentException();
        }

        double newPrice = netPrice - netPrice * percent / 100;

        if (newPrice < 0.01) {
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

    public ProductMethodsObject addTags(String... tags) {

        for (String tag : tags) {
            addTag(tag);
        }

        return this;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof ProductMethodsObject product)) {
            return false;
        }

        return sku.equals(product.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s - netto %.2f / brutto %.2f zł",
                sku,
                name,
                netPrice,
                getGrossPrice()
        );
    }
}