package org.example.salesreports;

import java.time.LocalDate;
import java.util.List;

public record Order(String id, String customer, LocalDate date, List<OrderLine> lines) {

}
