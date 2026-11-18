package org.example.product;

import java.util.List;

public class DigitalProduct extends Product {

    private String downloadUrl;

    public DigitalProduct(String sku, String name, Money netPrice, List<String> tags, String downloadUrl, int stock,
                          Supplier supplier) {
        super(sku, name, netPrice, tags, Category.BOOKS, stock, supplier);
        this.downloadUrl = downloadUrl;

    }

    public DigitalProduct(String sku, String name, Money netPrice, List<String> tags, String downloadUrl) {
        this(sku, name, netPrice, tags, downloadUrl, 0, null);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (plik do pobrania)";
    }

    @Override
    public Money getShippingCost() {
        return Money.zero("PLN");
    }

    @Override
    public String getLabel() {
        return String.format("%s [%s] - %s (z wysyłką %s)", getName(), getCategory().getDisplayName(), getNetPrice(),
                getTotalPrice());
    }
}
