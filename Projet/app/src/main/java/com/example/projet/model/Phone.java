package com.example.projet.model;

public class Phone {

    private final String number;
    private final String label;

    public Phone(String number, String label)
    {
        this.number = number;
        this.label = label;
    }

    public String getNumber() {
        return number;
    }

    public String getLabel() {
        return label;
    }
}
