package org.example.product;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main(String[] args) {
        ProductClass productClass = new ProductClass(
                "elec-001",
                "Laptop",
                4000,
                new ArrayList<>(List.of("elektronika"))
        );
        System.out.println(productClass);

        productClass.applyDiscount(10);
        System.out.println("Po rabacie: " + productClass.getNetPrice());

        productClass.raisePriceBy(10);
        System.out.println("Po podwyżce: " + productClass.getNetPrice());

        try {
            productClass.getTags().add("promocja");
        } catch (Exception e) {
            System.out.println("Nie można modyfikować listy tagów");
        }

        try {
            new ProductClass("", "Laptop", 1000, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            System.out.println("Pusty SKU odrzucony");
        }

        try {
            new ProductClass("ABC", "", 1000, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            System.out.println("Ujemna cena odrzucona");
        }
    }
}
