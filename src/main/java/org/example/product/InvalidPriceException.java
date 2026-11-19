package org.example.product;

public class InvalidPriceException extends ShopException{

    private final Money price;

    public InvalidPriceException(Money price) {
        super("Nieprawidłowa cena produktu: " + price);
        this.price = price;
    }

    public Money getPrice() {
        return price;
    }
}
