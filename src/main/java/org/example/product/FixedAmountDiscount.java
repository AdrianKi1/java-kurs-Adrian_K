package org.example.product;

public class FixedAmountDiscount implements DiscountPolicy {

    private double amount;

    public FixedAmountDiscount(double amount) {
        this.amount = amount;
    }

    @Override
    public double apply(double price) {
        return Math.max(price - amount, 0.01);
    }

    @Override
    public String getName() {
        return amount + "zł";
    }
}
