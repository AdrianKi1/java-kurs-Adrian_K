package org.example.orderSystem;

import java.util.*;

public class OrderQueue {

    private PriorityQueue<Order> pending = new PriorityQueue<>(Comparator.comparingInt(Order::priority).reversed());
    private Deque<Order> completed = new ArrayDeque<>();
    private Map<String, Status> statuses = new HashMap<>();

    public void submit(Order o) {
        pending.add(o);
        statuses.put(o.id(), Status.NOWE);
    }

    public Optional<Order> processNext() {
        Order order = pending.poll();

        if (order == null) {
            return Optional.empty();
        }

        statuses.put(order.id(), Status.ZREALIZOWANE);
        completed.addFirst(order);

        if (completed.size() > 10) {
            completed.pollLast();
        }

        return Optional.of(order);
    }

    public Optional<Order> peekNext() {
        return Optional.ofNullable(pending.peek());
    }

    public Optional<Order> undoLast() {
        Order order = completed.pollFirst();

        if (order == null) {
            return Optional.empty();
        }

        pending.add(order);
        statuses.put(order.id(), Status.NOWE);

        return Optional.of(order);
    }

    public List<Order> getHistory() {
        return new ArrayList<>(completed);
    }

    public Map<Status, List<String>> groupByStatus() {
        Map<Status, List<String>> result = new EnumMap<>(Status.class);

        for (Map.Entry<String, Status> entry : statuses.entrySet()) {
            result.computeIfAbsent(entry.getValue(), k->new ArrayList<>()).add(entry.getKey());
        }

        return result;
    }

    public Set<String> findCustomersWithPendingOrders() {
        Set<String> customer = new HashSet<>();

        for (Order order : pending) {
            customer.add(order.customer());
        }

        return customer;
    }

    public List<Order> getPendingOrders() {
        return new ArrayList<>(pending);
    }
}
