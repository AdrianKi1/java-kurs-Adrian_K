package org.example.functional;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = List.of(
                new Product("LAPT-001", "Laptop", new BigDecimal("3500"), 5, "ELEKTRONIKA"),
                new Product("MOU-002", "Myszka", new BigDecimal("50"), 10, "ELEKTRONIKA"),
                new Product("FOOD-003", "Chleb", new BigDecimal("8"), 0, "SPOŻYWCZE"),
                new Product("MONI-004", "Monitor", new BigDecimal("800"), 3, "ELEKTRONIKA"),
                new Product("TVXX-005", "Telewizor", new BigDecimal("1500"), 0, "ELEKTRONIKA"));

        System.out.println("Dostępne i tanie:");
        ProductFilters.filter(products, ProductFilters.IN_STOCK.and(ProductFilters.CHEAP)).forEach(System.out::println);
        System.out.println("Tanie lub drogie:");
        ProductFilters.filter(products, ProductFilters.CHEAP.or(ProductFilters.EXPENSIVE)).forEach(System.out::println);
        System.out.println("Niedostępne:");
        ProductFilters.filter(products, ProductFilters.IN_STOCK.negate()).forEach(System.out::println);
        System.out.println("Dostępna elektronika 100-1000 zł:");
        ProductFilters.filter(products, ProductFilters.IN_STOCK.and(product -> product.category().
                equals("ELEKTRONIKA")).and(ProductFilters.priceBetween(new BigDecimal("100"), new BigDecimal("1000")))).
                forEach(System.out::println);

        System.out.println("Cena produktu z VAT i wysyłką:");
        products.forEach(product -> System.out.println(product.name() + ": " + PriceCalculator.fullPriceLabel.
                apply(product)));

        System.out.println("Cena po rabacie 10%:");
        BigDecimal discountedPrice = PriceCalculator.discount(new BigDecimal("10")).apply(new BigDecimal("200"));
        System.out.println(PriceCalculator.FORMAT.apply(discountedPrice));

        System.out.println(PriceCalculator.FORMAT.apply(discountedPrice));

        DiscountPolicy premiumDiscount = order -> order.customer().premium() ? order.items().stream().
                map(Product::price).reduce(BigDecimal.ZERO, BigDecimal::add).
                multiply(new BigDecimal("0.10")) : BigDecimal.ZERO;

        DiscountPolicy highValueDiscount = order -> {BigDecimal total = order.items().stream().map(Product::price)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            return total.compareTo(new BigDecimal("500")) > 0 ? total.
                    multiply(new BigDecimal("0.05")) : BigDecimal.ZERO;};

        DiscountPolicy manyItemsDiscount = order -> order.items().size() > 5 ? new BigDecimal("20") : BigDecimal.ZERO;

        DiscountPolicy combinedDiscount = premiumDiscount.andThen(highValueDiscount).andThen(manyItemsDiscount);

        Customer premiumCustomer = new Customer("C002", "Anna Nowak", "anna@example.com", true, 10);

        List<Product> discountProducts = List.of(
                new Product("PROD-001", "Produkt 1", new BigDecimal("200"), 5, "ELEKTRONIKA"),
                new Product("PROD-002", "Produkt 2", new BigDecimal("200"), 5, "ELEKTRONIKA"),
                new Product("PROD-003", "Produkt 3", new BigDecimal("200"), 5, "ELEKTRONIKA"),
                new Product("PROD-004", "Produkt 4", new BigDecimal("200"), 5, "ELEKTRONIKA"),
                new Product("PROD-005", "Produkt 5", new BigDecimal("200"), 5, "ELEKTRONIKA"),
                new Product("PROD-006", "Produkt 6", new BigDecimal("200"), 5, "ELEKTRONIKA"));

        Order discountOrder = new Order("ORD-TEST", premiumCustomer, discountProducts);
        BigDecimal totalDiscount = combinedDiscount.apply(discountOrder);
        System.out.println("Łączny rabat: " + PriceCalculator.FORMAT.apply(totalDiscount));
    }
}