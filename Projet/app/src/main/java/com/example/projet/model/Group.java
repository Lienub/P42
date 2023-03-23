package com.example.projet.model;

import java.util.List;

public class Group {

    private List<Contact> contacts;
    private final String title;

    public Group(String title)
    {
        this.title = title;
    }

    public Group(String title, List<Contact> contacts)
    {
        this.title = title;
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public String getTitle() {
        return title;
    }

    public void addContact(Contact contact)
    {
        this.contacts.add(contact);
        contact.addGroup(this);
    }

    public void removeContact(Contact contact)
    {
        if(this.contacts.contains(contact))
        {
            this.contacts.remove(contact);
            contact.removeGroup(this);
        }
    }
}
