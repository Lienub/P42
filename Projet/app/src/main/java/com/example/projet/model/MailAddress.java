package com.example.projet.model;

public class MailAddress {

    private final String address;
    private final String label;

    public MailAddress(String address, String label)
    {
        this.address = address;
        this.label = label;
    }

    public String getAddress() {
        return address;
    }

    public String getLabel() {
        return label;
    }
}
