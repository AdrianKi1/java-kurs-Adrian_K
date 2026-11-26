package org.example.salesreports;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class SalesReport {

    private final List<Product> products;
    private final List<Order> orders;

    public SalesReport(List<Product> products, List<Order> orders) {
        this.products = products;
        this.orders = orders;
    }

    public List<Product> availableSorted() {
        return products.stream().filter(product -> product.stock() > 0).sorted(Comparator.
                comparing(Product::price)).toList();
    }

    public List<String> namesInCategory(String category) {
        return products.stream().filter(product -> product.category().equals(category)).map(Product::name).
                sorted().toList();
    }

    public Optional<Product> mostExpenisve() {
        return products.stream().max(Comparator.comparing(Product::price));
    }

    public Optional<Product> bestRatedInCategory(String category) {
        return products.stream().filter(product -> product.category().equals(category)).max(Comparator.
                comparingDouble(Product::rating));
    }

    public List<Product> page(int pageNumber, int pageSize) {
        return products.stream().sorted(Comparator.comparing(Product::name)).skip((long) pageNumber * pageSize).
                limit(pageSize).toList();
    }

    public boolean allRated() {
        return products.stream().allMatch(product -> product.rating() > 0);
    }

    public boolean hasOutOfStock() {
        return products.stream().anyMatch(product -> product.stock() == 0);
    }

    public String categoryList() {
        return products.stream().map(Product::category).distinct().sorted().collect(Collectors.
                joining(", ", "[", "]"));
    }

    public Map<String, List<Product>> byCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category));
    }

    public Map<String, Long> countByCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category, Collectors.counting()));
    }

    public Map<String, Double> averageRatingByCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category, Collectors.
                averagingDouble(Product::rating)));
    }

    public Map<String, List<String>> productNamesByCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category, Collectors.
                mapping(Product::name, Collectors.toList())));
    }

    public Map<String, Optional<Product>> mostExpensiveByCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category, Collectors.
                maxBy(Comparator.comparing(Product::price))));
    }

    public Map<Boolean, List<Product>> splitByAvailability() {
        return products.stream().collect(Collectors.partitioningBy(product -> product.stock() > 0));
    }

    public TreeMap<String, BigDecimal> stockValueByCategory() {
        return products.stream().collect(Collectors.groupingBy(Product::category, TreeMap::new, Collectors.
                reducing(BigDecimal.ZERO, product -> product.price().multiply(BigDecimal.
                        valueOf(product.stock())), BigDecimal::add)));
    }

    public List<Product> allOrderedProducts() {
        return orders.stream().flatMap(order -> order.lines().stream()).map(OrderLine::product).distinct().toList();
    }

    public BigDecimal orderTotal(Order order) {
        return order.lines().stream().map(line -> line.product().price().multiply(BigDecimal.
                valueOf(line.quantity()))).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Map<String, BigDecimal> revenueByCustomer() {
        return orders.stream().collect(Collectors.groupingBy(Order::customer, Collectors.reducing(BigDecimal.ZERO,
                this::orderTotal, BigDecimal::add)));
    }

    public Map<String, Integer> unitsSoldByProduct() {
        return orders.stream().flatMap(order -> order.lines().stream()).collect(Collectors.
                groupingBy(line -> line.product().sku(), Collectors.summingInt(OrderLine::quantity)));
    }

    public List<Product> top3BestSelling() {
        Map<String, Integer> sold = unitsSoldByProduct();

        return allOrderedProducts().stream().sorted(Comparator.comparingInt((Product product) -> sold.
                getOrDefault(product.sku(), 0)).reversed()).limit(3).toList();
    }

    public Optional<Order> largestOrder() {
        return orders.stream().max(Comparator.comparing(this::orderTotal));
    }

    public IntSummaryStatistics stockStatics() {
        return products.stream().mapToInt(Product::stock).summaryStatistics();
    }

    public Map<String, DoubleSummaryStatistics> priceStatsByCategory() {
        return  products.stream().collect(Collectors.groupingBy(Product::category, Collectors.
                summarizingDouble(product -> product.price().doubleValue())));
    }

    public String textReport() {
        return products.stream().collect(Collectors.groupingBy(Product::category)).entrySet().stream().map(entry -> {
            List<Product> categoryProducts = entry.getValue();

            double averageRating = categoryProducts.stream().mapToDouble(Product::rating).average().orElse(0.0);

            String mostExpensive = categoryProducts.stream().max(Comparator.comparing(Product::price)).
                    map(Product::name).orElse("Brak");

            return String.format(Locale.US, "%s: %d produktów, średnia ocena %.1f, najdroższy: %s",
                    entry.getKey(), categoryProducts.size(), averageRating, mostExpensive);
        }).collect(Collectors.joining("\n"));
    }
}
