package org.example.functional;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class OrderProcessor {

    private int orderCounter = 1;

    public final List<String> audiLog = new ArrayList<>();

    public final Consumer<Order> LOD_TO_CONSOLE = order -> System.out.println("Przetwarzanie zamówienia: " + order.id());

    public final Consumer<Order> ADD_TO_AUDTI = order -> audiLog.add("Zamówienie: " + order.id());

    public final Consumer<Order> SEND_CONFIRMATION = order -> System.out.println("Wysłąno potwierdzenie na email: "
            + order.customer().email());

    public Consumer<Order> buildPipeline(boolean withAudit, boolean withEmail) {
        Consumer<Order> pipeline = LOD_TO_CONSOLE;
        if (withAudit) {
            pipeline = pipeline.andThen(ADD_TO_AUDTI);
        }

        if (withEmail) {
            pipeline = pipeline.andThen(SEND_CONFIRMATION);
        }

        return pipeline;
    }

    public final Supplier<String> generateOrderId = () -> "ORD-" + orderCounter++;

    public Order createOrder(Customer customer, List<Product> items, Supplier<String> idGenerator) {
        return new Order(idGenerator.get(), customer, items);
    }
}
