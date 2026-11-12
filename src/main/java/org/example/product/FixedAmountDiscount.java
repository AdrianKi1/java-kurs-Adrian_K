package org.example.product;

public class FixedAmountDiscount implements DiscountPolicy {

    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public Money apply(Money price) {
        Money discount = Money.of(String.valueOf(amount), "PLN");
        return price.subtract(discount);
    }

    @Override
    public String getName() {
        return amount + " zł";
    }
}