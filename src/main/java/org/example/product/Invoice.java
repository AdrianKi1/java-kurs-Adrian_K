package org.example.product;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Invoice {

    private final List<InvoiceLine> lines = new ArrayList<>();

    public void addLine(InvoiceLine line) {
        lines.add(line);
    }

    public Money getTotalNet() {
        Money total = Money.zero("PLN");
        for (InvoiceLine line : lines) {
            total = total.add(line.netValue());
        }

        return total;
    }

    public Money getTotalVat() {
        Money total = Money.zero("PLN");
        for (InvoiceLine line : lines) {
            total = total.add(line.vatValue());
        }
        return total;
    }

    public Money getTotalGross() {
        Money total = Money.zero("PLN");
        for (InvoiceLine line : lines) {
            total = total.add(line.grossValue());
        }

        return total;
    }

    public Map<Category, Money> getVatSummary() {

        Map<Category, Money> summary = new EnumMap<>(Category.class);

        for (InvoiceLine line : lines) {
            Category category = line.product().category();
            summary.put(category, summary.getOrDefault(category, Money.zero("PLN")).add(line.vatValue()));
        }
        return summary;
    }

    public String render() {
        StringBuilder sb = new StringBuilder();

        sb.append("""
                ===== FAKTURA =====
                
                Nazwa                 Ilość      Netto         Brutto
                """);

        for (InvoiceLine line : lines) {

            sb.append(String.format(
                            "%-20s %5d %15s %15s%n",
                            line.product().name(),
                            line.quantity(),
                            line.netValue(),
                            line.grossValue()));
        }

        sb.append("\n");
        sb.append("Netto: ").append(getTotalNet()).append("\n");
        sb.append("VAT: ").append(getTotalVat()).append("\n");
        sb.append("Brutto: ").append(getTotalGross()).append("\n");

        return sb.toString();
    }
}