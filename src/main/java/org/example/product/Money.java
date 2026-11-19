package org.example.product;

import com.sun.nio.sctp.NotificationHandler;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Money(BigDecimal amount, String currency) {
    public Money {
        if (amount == null) {
            throw new ShopException("Amount cannot be null");
        }

        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new ShopException("Amount cannot be negative");
        }

        if (currency == null || currency.trim().isEmpty()) {
            throw new ShopException("Currency cannot be empty");
        }

        currency = currency.trim().toUpperCase();
        amount = amount.setScale(2, RoundingMode.HALF_UP);
    }

    public Money add(Money other) {
        if (!currency.equals(other.currency)) {
            throw new ShopException("Different currencies");
        }

        return new Money(amount.add(other.amount()), currency);
    }

    public Money subtract(Money other) {
        if (!currency.equals(other.currency())) {
            throw new ShopException("Different currencies");
        }

        BigDecimal result = amount.subtract(other.amount());
        if (result.compareTo(BigDecimal.ZERO) < 0) {
            throw new ShopException("Negative result");
        }

        return new Money(result, currency);
    }

    public Money multiply(int quantity) {
        return new Money(amount.multiply(BigDecimal.valueOf(quantity)), currency);
    }

    public Money applyPercent(double percent) {
        BigDecimal multiplier = BigDecimal.valueOf(1 + percent / 100);
        return new Money(amount.multiply(multiplier), currency);
    }

    public boolean isGreaterThan(Money other) {
        if (!currency.equals(other.currency())) {
            throw new ShopException("Different currencies");
        }

        return amount.compareTo(other.amount()) > 0;
    }

    public static Money zero(String currency) {
        return new Money(BigDecimal.ZERO, currency);
    }

    public static Money of(String amount, String currency) {
        return new Money(new BigDecimal(amount), currency);
    }

    @Override
    public String toString() {
        String value = amount.toPlainString().replace(".", ",");
        return value + " " + currency;
    }
}
