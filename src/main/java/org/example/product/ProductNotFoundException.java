package org.example.product;

public class ProductNotFoundException extends ShopException {

    private final String sku;

    public ProductNotFoundException(String sku) {
        super("Nie znaleziono produktu o SKU: " + sku);
        this.sku = sku;
    }

    public String getSku() {
        return sku;
    }
}
