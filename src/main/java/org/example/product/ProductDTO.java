package org.example.product;

public record ProductDTO(@NotBlank String sku, @NotBlank @MaxLength(20) String name,
                         @Positive Money netPrice, Category category) {
    public ProductDTO {
        if (sku != null) {
            sku= sku.trim().toUpperCase();
        }

    }
    public Money grossPrice() {
        return netPrice.applyPercent(category.getVatRate() * 100);
    }

    public static ProductDTO from(Product p) {
        return new ProductDTO(p.getSku(), p.getName(), p.getNetPrice(), p.getCategory());
    }
    public static ProductDTO fromCsvLine(String line) {
        String[] data = line.split(";");

        return new ProductDTO(data[0], data[1], Money.of(data[2], "PLN"), Category.valueOf(data[3]));
    }
}
