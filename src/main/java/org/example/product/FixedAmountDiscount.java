package org.example.product;

public class FixedAmountDiscount implements DiscountPolicy {

    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public Money apply(Money price) {
        return price;
    }

    @Override
    public String getName() {
        return amount + " zł";
    }
}