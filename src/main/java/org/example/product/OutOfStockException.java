package org.example.product;

public class OutOfStockException extends ShopException {

    private final String sku;
    private final int requested;
    private final int available;

    public OutOfStockException(String message, String sku, int requested, int available) {
        super("Brak wystarczającej ilości produktów o SKU: " + sku + ". Zamówiono " + requested + ", dostępne " +
                available);
        this.sku = sku;
        this.requested = requested;
        this.available = available;
    }

    public String getSku() {
        return sku;
    }

    public int getRequested() {
        return requested;
    }

    public int getAvailable() {
        return available;
    }
}
