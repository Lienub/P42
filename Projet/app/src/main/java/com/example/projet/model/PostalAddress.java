package com.example.projet.model;

public class PostalAddress {

    private final String id;
    private final String address;
    private final String city;
    private final String country;
    private final String label;

    public PostalAddress(String id, String address, String city, String country, String label)
    {
        this.id = id;
        this.address = address;
        this.city = city;
        this.country = country;
        this.label = label;
    }

    public String getId() {
        return id;
    }
    public String getAddress() {
        return address;
    }
    public String getCity() {
        return city;
    }
    public String getCountry() {
        return country;
    }
    public String getLabel() {
        return label;
    }
}
