package org.example.product;

public class PercentageDiscount implements DiscountPolicy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public Money apply(Money price) {
        return price;
    }

    @Override
    public String getName() {
        return percent + "%";
    }
}