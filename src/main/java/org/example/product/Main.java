package org.example.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Product laptop = (new Electronics(
                "E001",
                "Laptop",
                3999.99,
                List.of("elektronika"),
                24));
        Product monitor = (new Electronics(
                "E002",
                "Monitor",
                1499.00,
                List.of("elektronika"),
                36));
        Product mleko = (new FoodProduct(
                "F001",
                "Mleko",
                4.99,
                List.of("nabiał"),
                "20-08-2026"));
        Product jogurt = (new FoodProduct(
                "F002",
                "Jogurt",
                3.99,
                List.of("nabiał"),
                "01-09-2026"));
        Product minectraft = (new DigitalProduct(
                "D001",
                "Minecraft",
                99.99,
                List.of("gra"),
                "https://download.com"));

        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new PercentageDiscount(10));
        ShoppingCart cart3 = new ShoppingCart(new ElectronicsOnlyDiscount());

        Product[] products =  {laptop, monitor, mleko, jogurt, minectraft};

        for (Product product : products) {
            cart1.add(product);
            cart2.add(product);
            cart3.add(product);
        }
        System.out.println("=== BRAK RABATU ===");
        System.out.println(cart1.getReceipt());

        System.out.println();

        System.out.println("=== RABAT 10% ===");
        System.out.println(cart2.getReceipt());

        System.out.println();

        System.out.println("=== RABAT TYLKO DLA ELEKTRONIKI ===");
        System.out.println(cart3.getReceipt());
    }
}