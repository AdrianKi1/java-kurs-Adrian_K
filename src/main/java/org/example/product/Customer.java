package org.example.product;

public class Customer implements Identifiable<String> {

    private String id;
    private String name;


    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " " + name;
    }
}
