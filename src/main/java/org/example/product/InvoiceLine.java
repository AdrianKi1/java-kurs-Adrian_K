package org.example.product;

public record InvoiceLine(
        ProductDTO product, int quantity) {

    public Money netValue() {
        return product.netPrice().multiply(quantity);
    }
    public Money vatValue() {
        Money gross = grossValue();
        return gross.subtract(netValue());
    }
    public Money grossValue() {
        return netValue().applyPercent(product.category().getVatRate() * 100);
    }
}