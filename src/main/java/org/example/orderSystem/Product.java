package org.example.orderSystem;

import java.math.BigDecimal;
import java.util.Objects;

public record Product(String sku, String name, BigDecimal price, String category) implements Comparable<Product> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Product other)) return false;
        return Objects.equals(sku, other.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public int compareTo(Product other) {
        return this.name.compareTo(other.name);
    }
}
