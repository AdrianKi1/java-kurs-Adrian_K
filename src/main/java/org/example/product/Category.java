package org.example.product;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
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

    public Money calculateGross(Money net) {
        return net.applyPercent(vatRate * 100);
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
