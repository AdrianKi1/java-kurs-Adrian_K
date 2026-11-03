package org.example.product;

public class ElectronicsOnlyDiscount implements DiscountPolicy {

    @Override
    public double apply(double price) {
        return price - (price * 10 / 100);
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
