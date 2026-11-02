package org.example.product;

import java.util.List;

public class DigitalProduct extends Product {

    private String downloadUrl;

    public DigitalProduct(String sku, String name, double netPrice, List<String> tags, String downloadUrl) {
        super(sku, name, netPrice, tags);
        this.downloadUrl = downloadUrl;
    }
    public String getDescription() {
        return super.getDescription() + " (plik do pobrania)";
    }
    public double getShippingCost() {
        return 0;
    }
}
