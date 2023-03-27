package com.example.projet.model;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    private List<Group> groups;

    private final String id;
    private final String firstname;
    private final String lastname;
    private List<MailAddress> mail_addresses;
    private List<PostalAddress> postal_addresses;
    private List<Phone> phone_numbers;


    public Contact(String id,String firstname, String lastname)
    {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static ArrayList<Contact> createContactsList(int i) {
        ArrayList<Contact> contacts = null;
        for (int j = 0; j < i; j++) {
            Contact contact = new Contact("1", "firstname" + j, "lastname" + j);
            contacts.add(contact);
        }
        return contacts;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public String getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public List<MailAddress> getMailAddresses() {
        return mail_addresses;
    }
    public List<PostalAddress> getPostalAddresses() {
        return postal_addresses;
    }
    public List<Phone> getPhoneNumbers() {
        return phone_numbers;
    }
    public void addGroup(Group group)
    {
        this.groups.add(group);
        group.addContact(this);
    }
    public void addMailAddress(MailAddress mail_address)
    {
        this.mail_addresses.add(mail_address);
    }
    public void addPostalAddress(PostalAddress postal_address)
    {
        this.postal_addresses.add(postal_address);
    }
    public void addPhoneNumber(Phone phone_number)
    {
        this.phone_numbers.add(phone_number);
    }
    public void removeGroup(Group group)
    {
        if(this.groups.contains(group))
        {
            this.groups.remove(group);
            group.removeContact(this);
        }
    }
    public void removeMailAddress(MailAddress mail_address)
    {
        this.mail_addresses.remove(mail_address);
    }
    public void removePostalAddress(PostalAddress postal_address)
    {
        this.postal_addresses.remove(postal_address);
    }
    public void removePhoneNumber(Phone phone_number)
    {
        this.phone_numbers.remove(phone_number);
    }
}
