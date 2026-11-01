package org.example.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Warehouse warehouse = new Warehouse();

        warehouse.add(new Electronics(
                "E001",
                "Laptop",
                3999.99,
                List.of("elektronika"),
                24));
        warehouse.add(new Electronics(
                "E002",
                "Monitor",
                1499.00,
                List.of("elektronika"),
                36));
        warehouse.add(new FoodProduct(
                "F001",
                "Mleko",
                4.99,
                List.of("nabiał"),
                "20-08-2026"));
        warehouse.add(new FoodProduct(
                "F002",
                "Jogurt",
                3.99,
                List.of("nabiał"),
                "01-09-2026"));
        warehouse.add(new DigitalProduct(
                "D001",
                "Minecraft",
                99.99,
                List.of("gra"),
                "https://download.com"));


        System.out.println("Liczba produktów: " + warehouse.count());
        System.out.println(warehouse.findBySku("E001").getLabel());
        System.out.println("\nWszystkie produkty: ");
        for (Product product : warehouse.getAll()) {
            System.out.println(product.getLabel());
        }
        System.out.println("\nWartość magazynu: %.2f zł %n");
        warehouse.getTotalValue();
    }
}