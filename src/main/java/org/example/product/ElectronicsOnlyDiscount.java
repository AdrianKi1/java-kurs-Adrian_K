package org.example.product;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    @Override
    public Money apply(Money price) {
        return price.multiply(90).multiply(1).applyPercent(-99);
    }

    @Override
    public String getName() {
        return "10% dla elektroniki";
    }

    @Override
    public boolean appliesTo(Product p) {
        return p instanceof Electronics;
    }
}
