package org.example.functional;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.function.Function;

public class PriceCalculator {

    public static final Function<Product, BigDecimal> BASE_PRICE = Product::price;

    public static final Function<BigDecimal, BigDecimal> WITH_VAT = price -> price.
            multiply(new BigDecimal("1.23"));

    public static final Function<BigDecimal, BigDecimal> WITH_SHIPPING = price -> price.
            add(new BigDecimal("15"));

    public static final Function<BigDecimal, String> FORMAT = price -> {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.forLanguageTag("pl-PL"));
        symbols.setMonetaryGroupingSeparator(' ');
        symbols.setDecimalSeparator(',');

        DecimalFormat formatter = new DecimalFormat("#,##0.00", symbols);
        return formatter.format(price) + " zł";};

    public static final Function<Product, String> fullPriceLabel = BASE_PRICE.andThen(WITH_VAT).andThen(WITH_SHIPPING).
            andThen(FORMAT);

    public static final Function<BigDecimal, BigDecimal> discount(BigDecimal percent) {
        BigDecimal multiplier = BigDecimal.ONE.subtract(percent.divide(new BigDecimal("100")));

        return price -> price.multiply(multiplier);
    }
}
