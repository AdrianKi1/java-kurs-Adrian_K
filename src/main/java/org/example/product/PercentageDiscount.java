package org.example.product;

public class PercentageDiscount implements DiscountPolicy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public Money apply(Money price) {
        Money gross = price.applyPercent(percent);
        Money discount = gross.subtract(price);
        return price.subtract(discount);
    }

    @Override
    public String getName() {
        return percent + "%";
    }
}