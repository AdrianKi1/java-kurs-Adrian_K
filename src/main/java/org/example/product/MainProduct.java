package org.example.product;

import java.util.HashSet;
import java.util.Set;

public class MainProduct {

    public static void main(String[] args) {

        ProductMethodsObject p1 =
                ProductMethodsObject.of(
                        "ELEC-001",
                        "Laptop",
                        3999
                );

        ProductMethodsObject p2 =
                ProductMethodsObject.fromCsvLine(
                        "ELEC-002;Monitor;999.99"
                );

        ProductMethodsObject p3 =
                ProductMethodsObject.of(
                        "ELEC-003",
                        "Klawiatura",
                        199
                ).addTags(
                        "komputer",
                        "akcesoria"
                );

        ProductMethodsObject copy =
                new ProductMethodsObject(p1);

        System.out.println(
                "Liczba utworzonych obiektów: "
                        + ProductMethodsObject.getCreatedCount()
        );

        Set<ProductMethodsObject> products =
                new HashSet<>();

        products.add(p1);
        products.add(p2);
        products.add(p3);

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
                "p1.equals(copy): "
                        + p1.equals(copy)
        );

        System.out.println(
                "p1 == copy: "
                        + (p1 == copy)
        );
        try {
            ProductMethodsObject.fromCsvLine(
                    "ELEC-001;Laptop"
            );
        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Błąd CSV: "
                            + e.getMessage()
            );
        }
    }
}
