package org.example.product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Utils {
    public static <T extends  Comparable<T>> List<T> sortedCopy(List<T> list) {
        List<T> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
    }

    public static double sumPrices(List<? extends Product> products) {
        double sum = 0;
        for (Product product : products) {
            sum += product.getTotalPrice();
        }
        return sum;
    }
}
