package org.example.product;

public record ProductDTO(String sku, String name, double netPrice, Category category) {
    public ProductDTO {
        if (sku == null || sku.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid sku");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name");
        }
        if (netPrice <= 0) {
            throw new IllegalArgumentException("Invalid price");
        }
        sku= sku.trim().toUpperCase();
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
