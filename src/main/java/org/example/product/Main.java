package org.example.product;

import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.EnumMap;

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

        System.out.println();
        List<ProductDTO> csvProducts = List.of(ProductDTO.fromCsvLine("ELEC-001;Laptop;3999.00;ELECTRONICS"),
                ProductDTO.fromCsvLine("ELEC-002;Monitor;1499.00;ELECTRONICS"),
                ProductDTO.fromCsvLine("FOOD-001;Mleko;4.99;FOOD"),
                ProductDTO.fromCsvLine("BOOK-001;Java Podstawy;89.99;BOOKS"),
                ProductDTO.fromCsvLine("ALC-001;Piwo;6.99;ALCOHOL"));

        System.out.println("=== RAPORT CEN BRUTTO ===");
        for (ProductDTO product : csvProducts) {
            System.out.printf("%s -> %.2f zł%n", product.name(), product.grossPrice());
        }
        EnumMap<Category, Integer> categoryCount = new EnumMap<>(Category.class);

        for (ProductDTO product : csvProducts) {
            Category category = product.category();

            categoryCount.put(category,categoryCount.getOrDefault(category, 0) + 1);
        }

        System.out.println();
        System.out.println("=== SZTUKI W KATEGORIACH ===");

        for (Category category : categoryCount.keySet()) {
            System.out.println(category.getDisplayName() + ":" + categoryCount.get(category));

        }
        System.out.println();
        System.out.println("=== PRODUKTY WYMAGAJĄCE WERYFIKACJI WIEKU ===");
        for (ProductDTO product : csvProducts) {
            if (product.category().isRequiresAgeVerification()) {
                System.out.println(product.name());
            }
        }
        Receipt receipt = new Receipt("CART-001", csvProducts);
        System.out.println();
        System.out.println("=== RECEIPT ===");
        System.out.println(receipt);
    }
}