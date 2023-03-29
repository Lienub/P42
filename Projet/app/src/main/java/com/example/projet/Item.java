package com.example.projet;

public class Item {
    String name;
    String surname;

    public Item(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setsurname(String surname) {
        this.surname = surname;
    }

    public String getsurname() {
        return surname;
    }
}
