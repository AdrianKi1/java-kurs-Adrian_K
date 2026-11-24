package org.example.orderSystem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OrderService {

    private Catalog catalog;
    private PriceList priceList;
    private OrderQueue orderQueue;

    public OrderService(Catalog catalog, PriceList priceList, OrderQueue orderQueue) {
        this.catalog = catalog;
        this.priceList = priceList;
        this.orderQueue = orderQueue;
    }

    public BigDecimal calculateTotal(Order o) {
        BigDecimal total = BigDecimal.ZERO;

        for (String sku : o.skus()) {
            Product product = catalog.findBySku(sku).orElseThrow();
            total = total.add(product.price());
        }

        BigDecimal discount = priceList.getDiscountFor(o.skus().size());
        BigDecimal discountAmount = total.multiply(discount).divide(new BigDecimal("100"), 2,
                RoundingMode.HALF_UP);

        return total.subtract(discountAmount);
    }

    public Set<String> findUnavailableSkus(Order o) {
        Set<String> unvailable = new HashSet<>();

        for (String sku : o.skus()) {
            if(catalog.findBySku(sku).isEmpty()) {
                unvailable.add(sku);
            }
        }
        return unvailable;
    }

    public Map<String, Integer> countProductsInPendingOrders() {
        Map<String, Integer> result = new HashMap<>();

        for (Order order : orderQueue.getPendingOrders()) {
            for (String sku : order.skus()) {
                result.merge(sku, 1, Integer::sum);
            }
        }

        return result;
    }
}
