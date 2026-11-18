package org.example.product;

import java.util.*;

public class SupplyChainService {

    private final ProductWarehouse warehouse;

    public SupplyChainService(ProductWarehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Optional<String> findSupplierName(String sku) {
        return warehouse.findById(sku).flatMap(Product::findSupplier).map(Supplier::name);
    }

    public Optional<String> findSupplierEmail(String sku) {
        return warehouse.findById(sku).flatMap(Product::findSupplier).flatMap(Supplier::findEmail);
    }

    public Optional<String> findSupplierCity(String sku) {
        return warehouse.findById(sku).flatMap(Product::findSupplier).flatMap(Supplier::findAddress).
                map(Address::city).map(String::toUpperCase);
    }

    public String getSupplierLabel(String sku) {
        return warehouse.findById(sku).flatMap(Product::findSupplier).flatMap(supplier -> supplier.
                        findAddress().map(address -> "Dostawca: " + supplier.name() + " (" + address.city() + ")")).
                orElse("Brak danych dostawcy");
    }

    public Optional<String> findEmailForContact(String sku) {
        return warehouse.findById(sku).filter(product -> product.getStock() > 0).flatMap(Product::findSupplier).
                flatMap(Supplier::findEmail).filter(email -> email.contains("@")).
                filter(email -> !email.isBlank());
    }

    public Optional<Address> findAddressOfCheapest() {
        return warehouse.findCheapest().flatMap(Product::findSupplier).flatMap(Supplier::findAddress);
    }

    public String getShippingCity(String sku) {
        return warehouse.findById(sku).flatMap(Product::findSupplier).
                flatMap(Supplier::findAddress).map(Address::city).orElse("magazyn centralny");
    }

    public Optional<Product> findBySkuAndMinStock(String sku, int minStock) {
        return warehouse.findById(sku).filter(product -> product.getStock() >= minStock);
    }

    public List<String> collectSupplierCities() {
        Set<String> cities = new HashSet<>();

        for (Product product : warehouse.findAll()) {
            product.findSupplier().flatMap(Supplier::findAddress).map(Address::city).
                    filter(city -> !city.isBlank()).ifPresent(cities::add);
        }

        return new ArrayList<>(cities);
    }

    public Product getOrThrow(String sku) {
        return warehouse.findById(sku).
                orElseThrow(() -> new IllegalArgumentException("Nie znaleziono produktu o sku: " + sku));
    }
}