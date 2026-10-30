package org.example.product;

import java.util.List;

public class Electronics extends Product {

    private int warrantyMonths;

    public Electronics(String sku, String name, double netPrice, List<String> tags, int warrantyMonths) {
        super(sku, name, netPrice, tags);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " (gwarancja " + warrantyMonths + " mies.)";
    }
    public double getShippingCost() {
        if (getNetPrice() > 2000) {
            return 0;
        }
        return 19.99;
    }

}
