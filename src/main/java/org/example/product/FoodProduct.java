package org.example.product;

import java.util.List;

public class FoodProduct extends Product implements Perishable {
    private String expiryDate;

    public FoodProduct(String sku, String name, double netPrice, List<String> tags, String expiryDate) {
        super(sku, name, netPrice, tags);
        this.expiryDate = expiryDate;
    }
    public String getDescription() {
        return super.getDescription() + " (termin: " + expiryDate + ")";
    }
    public double getShippingCost() {
        return 29.99;
    }
    @Override
    public String getCategory() {
        return "Żywność";
    }

    @Override
    public boolean isExpired() {
        return false;
    }
}
