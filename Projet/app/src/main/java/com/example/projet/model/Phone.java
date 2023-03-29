package com.example.projet.model;

public class Phone {

    private final String id;
    private final String number;
    private final String label;

    public Phone(String id, String number, String label)
    {
        this.id = id;
        this.number = number;
        this.label = label;
    }

    public String getId() {
        return id;
    }
    public String getNumber() {
        return number;
    }
    public String getLabel() {
        return label;
    }
}
