package org.example.product;

import lombok.Getter;

@Getter
public class InvalidPriceException extends ShopException {
    private final Money price;

    public InvalidPriceException(Money price) {
        super("Nieprawidłowa cena: " + price);
        this.price = price;
    }
}
