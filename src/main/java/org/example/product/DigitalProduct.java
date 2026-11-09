package org.example.product;

import java.util.List;

public class DigitalProduct extends Product {

    private String downloadUrl;

    public DigitalProduct(String sku, String name, Money netPrice, List<String> tags, String downloadUrl) {
        super(sku, name, netPrice, tags, Category.BOOKS);
        this.downloadUrl = downloadUrl;
    }
    public String getDescription() {
        return super.getDescription() + " (plik do pobrania)";
    }
    public Money getShippingCost() {
        return Money.zero("PLN");
    }
}
