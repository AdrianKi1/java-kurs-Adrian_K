package org.example.orderSystem;

import java.util.List;

public record Order(String id, String customer, int priority, List<String> skus) {

}
