package org.example.product;

import java.util.HashSet;
import java.util.Set;

public class MainClass {

    public static void main(String[] args) {

        ProductClass product1 = ProductClass
                .of("ELEC-001", "Laptop", 3999.00)
                .addTags("elektronika", "promocja");

        ProductClass product2 =
                ProductClass.fromCsvLine(
                        "ELEC-002;Monitor;1499.00"
                );

        ProductClass product3 =
                ProductClass.of(
                        "ELEC-003",
                        "Klawiatura",
                        199.99
                );

        ProductClass copy =
                new ProductClass(product1);

        System.out.println(product1);
        System.out.println(product2);
        System.out.println(product3);

        System.out.println(
                "Liczba utworzonych obiektów: "
                        + ProductClass.getCreatedCount()
        );

        Set<ProductClass> products =
                new HashSet<>();

        products.add(product1);
        products.add(product2);
        products.add(product3);

        System.out.println(
                "Rozmiar zbioru: "
                        + products.size()
        );

        products.add(copy);

        System.out.println(
                "Rozmiar po dodaniu kopii: "
                        + products.size()
        );

        System.out.println(
                "equals(): "
                        + product1.equals(copy)
        );

        System.out.println(
                "== : "
                        + (product1 == copy)
        );

        try {
            ProductClass.fromCsvLine(
                    "ELEC-001;Laptop"
            );
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "Błędny CSV: "
                            + e.getMessage()
            );
        }
    }
}