package org.example.product;

import java.awt.font.FontRenderContext;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.EnumMap;

public class Main {

    public static void main(String[] args) {

        Product laptop = (new Electronics(
                "E001",
                "Laptop",
                Money.of("3999.99", "PLN"),
                List.of("elektronika"),
                24));
        Product monitor = (new Electronics(
                "E002",
                "Monitor",
                Money.of("1499.00", "PLN"),
                List.of("elektronika"),
                36));
        Product mleko = (new FoodProduct(
                "F001",
                "Mleko",
                Money.of("4.99", "PLN"),
                List.of("nabiał"),
                "20-08-2026"));
        Product jogurt = (new FoodProduct(
                "F002",
                "Jogurt",
                Money.of("3.99", "PLN"),
                List.of("nabiał"),
                "01-09-2026"));
        Product minecraft = (new DigitalProduct(
                "D001",
                "Minecraft",
                Money.of("99.99", "PLN"),
                List.of("gra"),
                "https://download.com"));

        Product[] products = {laptop, monitor, mleko, jogurt, minecraft};

        Address address = new Address("Kwiatowa 10", "Warszawa", "00-001");
        Supplier supplier = new Supplier("TechSupplier", address, "kontakt@techsupplier.pl");
        Product testProduct = new Electronics("E003", "Klawiatura", Money.of("199.99",
                "PLN"), List.of("elektronika"), 24, 15, supplier);

        ShoppingCart cart1 = new ShoppingCart(new NoDiscount());
        ShoppingCart cart2 = new ShoppingCart(new PercentageDiscount(10));
        ShoppingCart cart3 = new ShoppingCart(new ElectronicsOnlyDiscount());


        for (Product product : products) {
            cart1.add(product, 1);
            cart2.add(product, 1);
            cart3.add(product, 1);
        }
        System.out.println("=== BRAK RABATU ===");
        System.out.println(cart1.generateReceipt());

        System.out.println();

        System.out.println("=== RABAT 10% ===");
        System.out.println(cart2.generateReceipt());

        System.out.println();

        System.out.println("=== RABAT TYLKO DLA ELEKTRONIKI ===");
        System.out.println(cart3.generateReceipt());

        ProductWarehouse productWarehouse = new ProductWarehouse();

        productWarehouse.add(laptop);
        productWarehouse.add(monitor);
        productWarehouse.add(mleko);
        productWarehouse.add(jogurt);
        productWarehouse.add(minecraft);
        productWarehouse.add(testProduct);
        SupplyChainService supplyChainService = new SupplyChainService(productWarehouse);
        System.out.println();
        System.out.println("=== TEST ŁAŃCUCHA DOSTAW ===");
        System.out.println("Nazwa dostawcy: " + supplyChainService.findSupplierName("E003"));
        System.out.println("Email dostawcy: " + supplyChainService.findSupplierEmail("E003"));
        System.out.println("Miasto dostawcy: " + supplyChainService.findSupplierCity("E003"));

        System.out.println("Etykieta dostawcy: " + supplyChainService.getSupplierLabel("E003"));
        System.out.println("Email do kontaktu: " + supplyChainService.findEmailForContact("E003"));
        System.out.println("Adres dostawcy najtańszego produktu: " + supplyChainService.findAddressOfCheapest());
        System.out.println("Miasto wysyłki: " + supplyChainService.getShippingCity("E003"));
        System.out.println("Produkt z wymaganym stanem: " + supplyChainService.findBySkuAndMinStock("E003",
                10));
        System.out.println("Produkt z niewystarczjącym stanem: " + supplyChainService.findBySkuAndMinStock("E003",
                20));
        System.out.println("Miasta dostawców: " + supplyChainService.collectSupplierCities());
        System.out.println("Produkt znaleziony przez getOrThrow: " + supplyChainService.getOrThrow("E003"));

        System.out.println();
        System.out.println("=== Test Process Order ===");
        OrderResult orderResult = supplyChainService.processOrder(List.of("E001", "Brak-001", "E003", "Brak-002"));

        System.out.println("Przyjęte produkty: " + orderResult.accepted());
        System.out.println("Odrzucone pozycje: " + orderResult.rejected());

        System.out.println();
        System.out.println("=== MAGAZYN PRODUKTÓW ===");

        System.out.println("Liczba produktów: " +  productWarehouse.count());

        System.out.println(productWarehouse.findById("E001"));

        Warehouse<Customer> customerWarehouse = new Warehouse<>();

        customerWarehouse.add(new Customer("C001", "Jan Kowalski"));
        customerWarehouse.add(new Customer("C002", "Anna Nowak"));

        System.out.println();
        System.out.println("=== MAGAZYN KLIENTÓW ===");

        System.out.println("Liczba klientów: " + customerWarehouse.count());
        System.out.println(customerWarehouse.findById("C001"));

        List<Integer> numbers = List.of(5, 2, 8, 1);

        System.out.println();
        System.out.println("Posortowana lista: " + Utils.sortedCopy(numbers));

        System.out.printf("Suma cen produktów: " +
                Utils.sumPrices(productWarehouse.findAll()));

        System.out.println();
        List<ProductDTO> csvProducts = List.of(ProductDTO.fromCsvLine("ELEC-001;Laptop;3999.00;ELECTRONICS"),
                ProductDTO.fromCsvLine("ELEC-002;Monitor;1499.00;ELECTRONICS"),
                ProductDTO.fromCsvLine("FOOD-001;Mleko;4.99;FOOD"),
                ProductDTO.fromCsvLine("BOOK-001;Java Podstawy;89.99;BOOKS"),
                ProductDTO.fromCsvLine("ALC-001;Piwo;6.99;ALCOHOL"));

        System.out.println("=== RAPORT CEN BRUTTO ===");
        for (ProductDTO product : csvProducts) {
            System.out.printf("%s -> %s%n", product.name(), product.grossPrice());
        }
        EnumMap<Category, Integer> categoryCount = new EnumMap<>(Category.class);

        for (ProductDTO product : csvProducts) {
            Category category = product.category();

            categoryCount.put(category,categoryCount.getOrDefault(category, 0) + 1);
        }

        System.out.println();
        System.out.println("=== SZTUKI W KATEGORIACH ===");

        for (Category category : categoryCount.keySet()) {
            System.out.println(category.getDisplayName() + ":" + categoryCount.get(category));

        }
        System.out.println();
        System.out.println("=== PRODUKTY WYMAGAJĄCE WERYFIKACJI WIEKU ===");
        for (ProductDTO product : csvProducts) {
            if (product.category().isRequiresAgeVerification()) {
                System.out.println(product.name());
            }
        }
        Receipt receipt = new Receipt("CART-001", csvProducts);
        System.out.println();
        System.out.println("=== RECEIPT ===");
        System.out.println(receipt);

        System.out.println();


        System.out.println("=== WALIDACJA PRODUKTÓW ===");
        ProductDTO poprawny = new ProductDTO("ELEC-001", "Laptop", Money.of("3999.99", "PLN"),
                Category.ELECTRONICS);
        System.out.println();
        System.out.println("=== POPRAWNY PRODUKT===");
        System.out.println(ProductValidator.validate(poprawny));


        ProductDTO bledny = new ProductDTO("", "Bardzo długa nazwaaaaaaaaaaa produktu", Money.of("0",
                "PLN"),
                Category.ELECTRONICS);
        System.out.println();
        System.out.println("=== BŁĘDNY PRODUKT ===");
        System.out.println(ProductValidator.validate(bledny));

        Invoice invoice = new Invoice();

        invoice.addLine(new InvoiceLine(csvProducts.get(0), 1));
        invoice.addLine(new InvoiceLine(csvProducts.get(1), 2));
        invoice.addLine(new InvoiceLine(csvProducts.get(2), 5));
        invoice.addLine(new InvoiceLine(csvProducts.get(3), 1));
        invoice.addLine(new InvoiceLine(csvProducts.get(4), 10));

        System.out.println();
        System.out.println(invoice.render());
    }

}