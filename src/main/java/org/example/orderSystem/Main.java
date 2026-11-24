package org.example.orderSystem;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Catalog catalog = new Catalog();

        catalog.add(new Product("P001", "Laptop", new BigDecimal("3500.00"), "Elektronika"));
        catalog.add(new Product("P002", "Telefon", new BigDecimal("2200.00"), "Elektronika"));
        catalog.add(new Product("P003", "Monitor", new BigDecimal("900.00"), "Elektronika"));
        catalog.add(new Product("P004", "Biurko", new BigDecimal("700.00"), "Meble"));
        catalog.add(new Product("P005", "Krzesło", new BigDecimal("400.00"), "Meble"));
        catalog.add(new Product("P006", "Szafa", new BigDecimal("1200.00"), "Meble"));
        catalog.add(new Product("P007", "Książka Java", new BigDecimal("80.00"), "Książki"));
        catalog.add(new Product("P008", "Książka SQL", new BigDecimal("60.00"), "Książki"));

        PriceList priceList = new PriceList();

        priceList.addThreshold(2, new BigDecimal("5"));
        priceList.addThreshold(4, new BigDecimal("10"));
        priceList.addThreshold(6, new BigDecimal("15"));

        OrderQueue orderQueue = new OrderQueue();

        OrderService orderService = new OrderService(catalog, priceList, orderQueue);

        Order order1 = new Order("ORD-001", "Adam", 2, List.of("P001", "P002"));
        Order order2 = new Order("ORD-002", "Kasia", 5, List.of("P004", "P005", "P007"));
        Order order3 = new Order("ORD-003", "Marek", 1, List.of("P003"));
        Order order4 = new Order("ORD-004", "Ola", 4, List.of("P006", "P008"));
        Order order5 = new Order("ORD-005", "Tomek", 3, List.of("P002", "P003", "P007", "P008"));

        orderQueue.submit(order1);
        orderQueue.submit(order2);
        orderQueue.submit(order3);
        orderQueue.submit(order4);
        orderQueue.submit(order5);

        System.out.println("Przetworzone zamowienia:");
        orderQueue.processNext().ifPresent(System.out::println);
        orderQueue.processNext().ifPresent(System.out::println);
        orderQueue.processNext().ifPresent(System.out::println);

        System.out.println("\nWartosc zamowienia ORD-001: " + orderService.calculateTotal(order1));
        System.out.println("Brakujace produkty w ORD-001: " + orderService.findUnavailableSkus(order1));
        System.out.println("Liczba produktow w oczekujacych zamowieniach:");
        System.out.println(orderService.countProductsInPendingOrders());


    }
}
