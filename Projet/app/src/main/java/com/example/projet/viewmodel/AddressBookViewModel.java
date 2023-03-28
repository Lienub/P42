package com.example.projet.viewmodel;

import static com.example.projet.MainActivity.getContext;

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

    private final RequestQueue instance = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
    private MutableLiveData<List<Contact>> contacts;
    private MutableLiveData<List<Group>> groups;

    public LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>();
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
        JsonArrayRequest contactRequest = new JsonArrayRequest(
                apiBasename + "/person",
                response -> {
                    List<Contact> contacts = new ArrayList<>();
                    try {
                        if(response.length() == 0) {
                            Log.d("TEST", "no contacts");
                            return;
                        }

                        for (int i = 0; i < response.length(); i++) {

                            contacts.add(new Contact(
                                    response.getJSONObject(i).getString("id"),
                                    response.getJSONObject(i).getString("firstname"),
                                    response.getJSONObject(i).getString("lastname")
                            ));


                        }
                        Log.d("TEST", "loadContacts: " + contacts);
                        this.contacts.postValue(contacts);
                        Log.d("TEST", "loadContacts: " + this.contacts.getValue());

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        instance.add(contactRequest);
    }

    private void loadGroups() {
        JsonArrayRequest groupRequest = new JsonArrayRequest(
                apiBasename + "/group",
                response -> {
                    List<Group> groups = new ArrayList<>();
                    try {
                        if(response.length() == 0) {
                            Log.d("TEST", "no groups");
                            return;
                        }

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

        instance.add(groupRequest);
    }

}
