package org.example.product;

import java.util.ArrayList;
import java.util.List;

public enum Category {
    ELECTRONICS("Elektronika", 0.23, false),
    FOOD("Spożywcze", 0.05, false),
    ALCOHOL("Alkohol", 0.23, true),
    BOOKS("Książki", 0.05, false);

    private final String displayName;
    private final double vatRate;
    private final boolean requiresAgeVerification;

    Category(String displayName, double vatRate, boolean requiresAgeVerification) {
        this.displayName = displayName;
        this.vatRate = vatRate;
        this.requiresAgeVerification = requiresAgeVerification;
    }
    public String getDisplayName() {
        return displayName;
    }
    public double getVatRate() {
        return vatRate;
    }
    public boolean isRequiresAgeVerification() {
        return requiresAgeVerification;
    }
    public double calculateGross(double net) {
        return net * (1 + vatRate);
    }
    public static List<Category> withoutAgeCheck() {
        List<Category> result = new ArrayList<>();
        for (Category category : values()) {
            if (!category.requiresAgeVerification) {
                result.add(category);
            }
        }
        return result;
    }
}
