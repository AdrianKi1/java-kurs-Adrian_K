package org.example.product;

import java.util.List;

public class Electronics extends Product {

    private int warrantyMonths;

    public Electronics(String sku, String name, Money netPrice, List<String> tags, int warrantyMonths, int stock,
                       Supplier supplier) {
        super(sku, name, netPrice, tags, Category.ELECTRONICS, stock, supplier);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (gwarancja " + warrantyMonths + " mies.)";
    }

    @Override
    public Money getShippingCost() {
        if (getNetPrice().isGreaterThan(Money.of("2000", "PLN"))) {
            return Money.zero("PLN");
        }

        return Money.of("19.99", "PLN");
    }

    public Electronics(String sku, String name, Money netPrice, List<String> tags, int warrantyMonths) {
        this(sku, name, netPrice, tags, warrantyMonths, 0, null);
    }

    @Override
    public String getLabel() {
        return String.format("%s [%s] - %s (z wysyłką %s)", getName(), getCategory().getDisplayName(), getNetPrice(),
                getTotalPrice());
    }
}
