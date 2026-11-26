package org.example.salesreports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Product laptop = new Product("P001", "Laptop", "Elektronika", new BigDecimal("3500.00"),
                5, 4.8);

        Product mouse = new Product("P002", "Mysz", "Elektronika", new BigDecimal("100.00"), 0, 4.2);

        Product book = new Product("P003", "Java od podstaw", "Ksiazki", new BigDecimal("80.00"),
                10, 4.9);

        List<Product> products = List.of(laptop, mouse, book);

        Order order1 = new Order("O001", "Jan", LocalDate.now(), List.of(new OrderLine(laptop, 1),
                new OrderLine(mouse, 2)));

        Order order2 = new Order("O002", "Anna", LocalDate.now(), List.of(new OrderLine(book, 3)));

        List<Order> orders = List.of(order1, order2);

        SalesReport report = new SalesReport(products, orders);

        System.out.println("Dostępne produkty:");
        System.out.println(report.availableSorted());

        System.out.println("\nNajdroższy produkt:");
        System.out.println(report.mostExpenisve());

        System.out.println("\nLiczba produktów w kategoriach:");
        System.out.println(report.countByCategory());

        System.out.println("\nPrzychód według klientów:");
        System.out.println(report.revenueByCustomer());

        System.out.println("\nStatystyki stanów magazynowych:");
        System.out.println(report.stockStatics());

        System.out.println("\nRaport tekstowy:");
        System.out.println(report.textReport());
    }
}