package org.example.product;

public class InvalidPriceException extends ShopException{

    private final String price;

    public InvalidPriceException(String price) {
        super("Nieprawidłowa cena produktu: " + price);
        this.price = price;
    }

    public String getPrice() {
        return price;
    }
}
