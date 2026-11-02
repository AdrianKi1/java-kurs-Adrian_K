package org.example.product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        Product product1 = Product.of("ELEC-001", "Laptop", 3999.00)
                .addTags("elektronika", "promocja");

        Product product2 = Product.fromCsvLine("ELEC-002;Monitor;1499.00");

        Product product3 = Product.of("ELEC-003", "Klawiatura", 199.99);

        Product copy = new Product(product1);

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        System.out.println("Liczba utworzonych obiektów: " + Product.getCreatedCount());

        Set<Product> products = new HashSet<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        System.out.println("Rozmiar zbioru: " + products.size());

        products.add(copy);

        System.out.println("Rozmiar po dodaniu kopii: " + products.size());

        System.out.println("equals(): " + product1.equals(copy));

        System.out.println("== : " + (product1 == copy));

        try {Product.fromCsvLine("ELEC-001;Laptop");

        } catch (IllegalArgumentException e) {
            System.out.println("Błędny CSV: " + e.getMessage());
        }

        List<Product> productList = new ArrayList<>();
        productList.add(new Electronics(
                "E001",
                "Laptop",
                3999.99,
                List.of("elektronika"),
                24));
        productList.add(new Electronics(
                "E002",
                "Monitor",
                1499.00,
                List.of("elektronika"),
                36));
        productList.add(new FoodProduct(
                "F001",
                "Mleko",
                4.99,
                List.of("nabiał"),
                "20-08-2026"));
        productList.add(new FoodProduct(
                "F002",
                "Jogurt",
                3.99,
                List.of("nabiał"),
                "01-09-2026"));
        productList.add(new DigitalProduct(
                "D001",
                "Minecraft",
                99.99,
                List.of("gra"),
                "https://download.com"));

        double sumaKoszyka = 0;

        for (Product product : productList) {
            System.out.println(product.getDescription());

            System.out.printf("Cena całkowita: %.2f zł%n", product.getTotalPrice());

            sumaKoszyka += product.getTotalPrice();

            System.out.println();

        }
        System.out.printf("Suma koszyka: %.2f zł%n", sumaKoszyka);
    }
}