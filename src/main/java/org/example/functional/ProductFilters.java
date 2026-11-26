package org.example.functional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ProductFilters {

    public static final Predicate<Product> IN_STOCK = product -> product.stock() > 0;

    public static final Predicate<Product> CHEAP = product -> product.price().
            compareTo(new BigDecimal("100")) < 0;

    public static final Predicate<Product> EXPENSIVE = product -> product.price().
            compareTo(new BigDecimal("1000")) > 0;

    public static final Predicate<Product> HAS_VALID_SKU = product -> product.sku().
            matches("[A-Z]{4}-\\d{3}");

    public static Predicate<Product> priceAbove(BigDecimal min) {
        return product -> product.price().compareTo(min) > 0;
    }

    public static Predicate<Product> priceBetween(BigDecimal min, BigDecimal max) {
        return product -> product.price().compareTo(min) >= 0 && product.price().compareTo(max) < 0;
    }

    public static Predicate<Product> stockAtLeast(int min) {
        return  product -> product.stock() >= min;
    }

    public static List<Product> filter(List<Product> products, Predicate<Product> p) {
        List<Product> result = new ArrayList<>();

        for (Product product : products) {
            if (p.test(product)) {
                result.add(product);
            }
        }

        return result;
    }
}
