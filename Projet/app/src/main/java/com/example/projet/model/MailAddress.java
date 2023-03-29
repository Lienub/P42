package com.example.projet.model;

public class MailAddress {

    private final String id;
    private final String address;
    private final String label;

    public MailAddress(String id, String address, String label)
    {
        this.id = id;
        this.address = address;
        this.label = label;
    }

    public String getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getLabel() {
        return label;
    }
}
