package org.example.product;

import java.time.LocalDate;
import java.util.List;

public class FoodProduct extends Product implements Perishable {
    private String expiryDate;

    public FoodProduct(String sku, String name, Money netPrice, List<String> tags, String expiryDate, Supplier supplier,
                       int stock) {
        super(sku, name, netPrice, tags, Category.FOOD, supplier, stock);
        this.expiryDate = expiryDate;
    }
    public String getDescription() {
        return super.getDescription() + " (termin: " + expiryDate + ")";
    }
    public Money getShippingCost() {
        return Money.of("29.99", "PLN");
    }

    @Override
    public boolean isExpired() {
        LocalDate expiry = LocalDate.parse(expiryDate);
        return expiry.isBefore(LocalDate.now());
    }
    @Override
    public String getLabel() {
        return String.format("%s [%s] - %s (z wysyłką %s)", getName(), getCategory().getDisplayName(),
                getNetPrice(), getTotalPrice());
    }
}
