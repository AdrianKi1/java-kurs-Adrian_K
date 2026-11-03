package org.example.product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Warehouse <T extends Identifiable<String>> {

    private final List<T> items = new ArrayList<>();

    public void add(T item) {
        items.add(item);
    }
    public int count() {
        return items.size();
    }
    public Optional<T> findById(String id) {
        for (T item : items) {
            if (item.getId().equals(id)) {
                return Optional.of(item);
            }
        }
        return Optional.empty();
    }
    public List<T> findAll() {
        return new ArrayList<>(items);
    }
    public void remove(String id) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId().equals(id)) {
                items.remove(i);
                break;
            }
        }
    }
}
