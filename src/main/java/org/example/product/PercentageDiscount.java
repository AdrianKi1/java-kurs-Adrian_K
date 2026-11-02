package org.example.product;

public class PercentageDiscount implements DiscountPolicy {

    private double percent;

    public PercentageDiscount(double percent) {
        this.percent = percent;
    }

    @Override
    public double apply(double price) {
        return price - (price * percent / 100);
    }

    @Override
    public String getName() {
        return percent + "%";
    }
}
