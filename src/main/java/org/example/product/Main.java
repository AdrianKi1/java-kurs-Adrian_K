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

        Product[] products = {laptop, monitor, mleko, jogurt, minectraft};

        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new PercentageDiscount(10));
        ShoppingCart cart3 = new ShoppingCart(new ElectronicsOnlyDiscount());


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

        Warehouse<Product> productWarehouse = new Warehouse<>();

        productWarehouse.add(laptop);
        productWarehouse.add(monitor);
        productWarehouse.add(mleko);
        productWarehouse.add(jogurt);
        productWarehouse.add(minectraft);

        System.out.println();
        System.out.println("=== MAGAZYN PRODUKTÓW ===");

        System.out.println("Liczba produktów: " +  productWarehouse.count());

        System.out.println(productWarehouse.findById("E001"));

        Warehouse<Customer> customerWarehouse = new Warehouse<>();

        customerWarehouse.add(new Customer("C001", "Jan Kowalski"));
        customerWarehouse.add(new Customer("C002", "Anna Nowak"));

        System.out.println();
        System.out.println("=== MAGAZYN KLIENTÓW ===");

        System.out.println("Liczba klientów: " + customerWarehouse.count());
        System.out.println(customerWarehouse.findById("C001"));

        List<Integer> numbers = List.of(5, 2, 8, 1);

        System.out.println();
        System.out.println("Posortowana lista: " + Utils.sortedCopy(numbers));

        System.out.printf("Suma cen produktów: %.2f zł%n",
                Utils.sumPrices(productWarehouse.findAll()));
    }
}