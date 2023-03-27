package com.example.projet.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet.model.*;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressBookViewModel extends ViewModel {
    private static final String apiBasename = "http://10.0.2.2:3000";

    private final RequestQueue instance;
    private MutableLiveData<List<Contact>> contacts;
    private MutableLiveData<List<Group>> groups;

    public AddressBookViewModel(RequestQueue instance) {
        this.instance = instance;
    }

    public LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<List<Contact>>();
            loadContacts();
        }
        return contacts;
    }

    public LiveData<List<Group>> getGroups() {
        if (groups == null) {
            groups = new MutableLiveData<List<Group>>();
            loadGroups();
        }
        return groups;
    }

    private void loadContacts() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                apiBasename + "/person",
                response -> {
                    try {
                        List<Contact> contacts = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            Contact contact = new Contact(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("firstname"),
                                    response.getJSONObject(i).getString("lastname")
                            );

                            loadMailAddresses(contact);
                            loadPostalAddresses(contact);
                            loadPhones(contact);

                            contacts.add(contact);

                        }

                        this.contacts.setValue(contacts);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(jsonArrayRequest);
    }

    private void loadGroups() {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                apiBasename + "/group",
                response -> {
                    try {
                        List<Group> groups = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            groups.add(new Group(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("title")
                            ));
                        }

                        this.groups.setValue(groups);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(jsonArrayRequest);
    }

    private void loadMailAddresses(Contact contact) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/mailAddress",
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            contact.addMailAddress(new MailAddress(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("address"),
                                    response.getJSONObject(i).getString("label")
                            ));
                        }


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(jsonArrayRequest);
    }

    private void loadPostalAddresses(Contact contact) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/postalAddress",
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            contact.addPostalAddress(new PostalAddress(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("address"),
                                    response.getJSONObject(i).getString("city"),
                                    response.getJSONObject(i).getString("country"),
                                    response.getJSONObject(i).getString("label")
                            ));
                        }


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(jsonArrayRequest);

    }

    private void loadPhones(Contact contact) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/phone",
                response -> {
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            contact.addPhoneNumber(new Phone(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("number"),
                                    response.getJSONObject(i).getString("label")
                            ));
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(jsonArrayRequest);
    }
}
