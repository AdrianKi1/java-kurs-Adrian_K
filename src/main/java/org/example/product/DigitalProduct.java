package org.example.product;

import java.util.List;

public class DigitalProduct extends Product {

    private String downloadUrl;

    public DigitalProduct(String sku, String name, Money netPrice, List<String> tags, String downloadUrl) {
        super(sku, name, netPrice, tags, Category.BOOKS);
        this.downloadUrl = downloadUrl;
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
