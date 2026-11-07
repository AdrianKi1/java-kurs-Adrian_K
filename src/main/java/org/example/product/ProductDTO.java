package org.example.product;

public record ProductDTO(@NotBlank String sku, @NotBlank @MaxLength(20) String name,
                         @Positive double netPrice, Category category) {
    public ProductDTO {
        if (sku != null) {
            sku= sku.trim().toUpperCase();
        }
    }
    public double grossPrice() {
        return category.calculateGross(netPrice);
    }

    public static ProductDTO from(Product p) {
        return new ProductDTO(p.getSku(), p.getName(), p.getNetPrice(), p.getCategory());
    }
    public static ProductDTO fromCsLine(String line) {
        String[] data = line.split(";");

        return new ProductDTO(data[0], data[1], Double.parseDouble(data[2]), Category.valueOf(data[3]));
    }
}
