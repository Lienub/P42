package com.example.projet.viewmodel;

import static com.example.projet.MainActivity.getContext;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet.model.*;
import com.example.projet.ui.contact.ContactFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressBookViewModel extends ViewModel {
    private static final String apiBasename = "http://10.0.2.2:3000";

    private final RequestQueue instance = Volley.newRequestQueue(Objects.requireNonNull(getContext()));
    private MutableLiveData<List<Contact>> contacts;
    private MutableLiveData<List<Group>> groups;

    // Observe the LiveData and get the value of the list when it changes


    public LiveData<List<Contact>> getContacts() {
        if (contacts == null) {
            contacts = new MutableLiveData<>(new ArrayList<>());
            loadContacts();
        }
        return contacts;
    }

    public LiveData<List<Contact>> getContactsFromGroup(Group group){
        if (contacts == null) {
            contacts = new MutableLiveData<>(new ArrayList<>());
            loadContactsFromGroup(group);
        }
        return contacts;
    }

    public  LiveData<List<Group>> getGroups(LifecycleOwner viewLifecycleOwner) {
        if (groups == null) {
            groups = new MutableLiveData<>(new ArrayList<>());
            loadGroups();
        }
        groups.observe(viewLifecycleOwner , new Observer<List<Group>>() {
            public void onChanged(List<Group> groups) {
                // Use the list of groups here
                ContactFragment.allGroups = groups;
                // Do something with groupList
            }
        });
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

    public void loadGroups() {
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

    private void loadContactsFromGroup(Group group) {
        JsonArrayRequest contactRequest = new JsonArrayRequest(
                apiBasename + "/group/" + group.getId() + "/people",
                response -> {
                    List<Contact> contacts = new ArrayList<>();
                    try {
                        if (response.length() == 0) {
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
                        this.contacts.setValue(contacts);

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

    public void deletePerson(String idPerson) {
        String url = apiBasename + "/person/" + idPerson;
        StringRequest deletePersonRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Afficher dans la console que ça a bien marché
                        Log.d("TAG", "Dans le onResponse de deletePerson");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gestion de l'erreur
                Log.e("TAG", "Erreur :  ", error);
            }
        });

        instance.add(deletePersonRequest);
    }

    public void deleteGroup(String idGroup) {
        String url = apiBasename + "/group/" + idGroup;
        StringRequest deleteGroupRequest = new StringRequest(Request.Method.DELETE, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Afficher dans la console que ça a bien marché
                        Log.d("TAG", "Dans le onResponse de deleteGroup");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gestion de l'erreur
                Log.e("TAG", "Erreur :  ", error);
            }
        });

        instance.add(deleteGroupRequest);
    }

    private void deletePersonFromGroup(String idPerson, String idGroup){
        String url = apiBasename + "/person/" + idPerson + "/group/" + idGroup;
        StringRequest deletePersonFromGroupRequest = new StringRequest(Request.Method.DELETE,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Afficher dans la console que ça a bien marché
                        Log.d("TAG", "Dans le onResponse de deletePersonFromGroup");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gestion de l'erreur
                Log.e("TAG", "Erreur :  ", error);
            }});

        instance.add(deletePersonFromGroupRequest);

    }

    public void addPersonToGroup(String idPerson, String idGroup){
        String url = apiBasename + "/person/" + idPerson + "/group/" + idGroup;
        StringRequest addPersonToGroupRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Afficher dans la console que ça a bien marché
                        Log.d("TAG", "Dans le onResponse de addPersonToGroup");
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Gestion de l'erreur
                Log.e("TAG", "Erreur :  ", error);
            }});

        instance.add(addPersonToGroupRequest);

    }
}
