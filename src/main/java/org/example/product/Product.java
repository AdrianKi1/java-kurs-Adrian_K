package org.example.product;

import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Product implements Identifiable<String> {

    private static int createdCount = 0;

    private final String sku;
    private final String name;
    private Money netPrice;
    private final List<String> tags;
    private final Category category;

    protected Product(String sku, String name, Money netPrice, List<String> tags, Category category) {

        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid sku");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }

        if (netPrice == null) {
            throw new IllegalArgumentException("Invalid price");
        }

        this.sku = sku.trim().toUpperCase();
        this.name = name.trim();
        this.netPrice = netPrice;
        this.tags = new ArrayList<>(tags);
        this.category = category;

        createdCount++;
    }


    public static int getCreatedCount() {
        return createdCount;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public Money getNetPrice() {
        return netPrice;
    }


    }

    public String getDescription() {
        return String.format("%s - %s", getName(), getNetPrice());
    }


    public abstract Money getShippingCost();

    public Category getCategory() {
        return category;
    }

    public final Money getTotalPrice() {
        return getNetPrice().add(getShippingCost());
    }

    public void raisePriceBy(double percent) {

        if (percent <= 0) {
            throw new IllegalArgumentException();
        }

        netPrice = netPrice.applyPercent(percent);
    }

    public void applyDiscount(double percent) {

        if (percent <= 0 || percent >= 100) {
            throw new IllegalArgumentException();
        }
        Money increased = netPrice.applyPercent(percent);
        Money discount =increased.subtract(netPrice);
        netPrice = netPrice.subtract(discount);
    }

    public void addTag(String tag) {

        if (tag == null || tag.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        tags.add(tag.trim().toLowerCase());
    }

    public Product addTags(String... tags) {

        for (String tag : tags) {
            addTag(tag);
        }

        return this;
    }

    public List<String> getTags() {
        return Collections.unmodifiableList(tags);
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (!(o instanceof Product)) {
            return false;
        }

        Product that = (Product) o;

        return sku.equals(that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku);
    }

    @Override
    public String toString() {
        return String.format(
                "%s %s - netto %s / brutto %s",
                sku,
                name,
                getNetPrice(),
                getGrossPrice()
        );
    }

    public final String getLabel() {
        return String.format(
                "%s [%s] - %s (z wysyłką %s)",
                getName(),
                getCategory().getDisplayName(),
                getNetPrice(),
                getTotalPrice());
    }
    @Override
    public String getId() {
        return getSku();

    }
}