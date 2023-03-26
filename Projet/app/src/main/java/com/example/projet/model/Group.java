package com.example.projet.model;

import java.util.List;

public class Group {

    private List<Contact> contacts;
    private final String id;
    private final String title;

    public Group(String id, String title)
    {
        this.id = id;
        this.title = title;
    }
    public List<Contact> getContacts() {
        return contacts;
    }
    public String getId() {
        return id;
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
