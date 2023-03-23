package com.example.projet.model;

public class PostalAddress {

    private final String address;
    private final String city;
    private final String country;
    private final String label;

    public PostalAddress(String address, String city, String country, String label)
    {
        this.address = address;
        this.city = city;
        this.country = country;
        this.label = label;
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
