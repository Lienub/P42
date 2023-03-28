package com.example.projet.viewmodel;

import static com.example.projet.MainActivity.getContext;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet.MainActivity;
import com.example.projet.model.Contact;
import com.example.projet.model.Group;
import com.example.projet.model.MailAddress;
import com.example.projet.model.Phone;
import com.example.projet.model.PostalAddress;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InfosViewModel extends ViewModel {

    private static final String apiBasename = "http://10.0.2.2:3000";
    private final RequestQueue requestQueue =  Volley.newRequestQueue(Objects.requireNonNull(getContext()));
    private MutableLiveData<List<MailAddress>> mailAddresses;
    private MutableLiveData<List<PostalAddress>> postalAddresses;
    private MutableLiveData<List<Phone>> phones;
    private MutableLiveData<List<Group>> groups;

    public LiveData<List<Group>> getGroupsFromContact(Contact contact) {
        if (groups == null) {
            groups = new MutableLiveData<>(new ArrayList<>());
            loadGroupsFromContact(contact);
        }
        Log.d("TEST", "groups : " + groups.getValue().toString()  );
        return groups;
    }

    public LiveData<List<MailAddress>> getMailAddresses(Contact contact) {
        Log.d("TEST", "getMailAddresses called");
        if (mailAddresses == null) {
            mailAddresses = new MutableLiveData<>(new ArrayList<>());

            loadMailAddresses(contact);
        }

        Log.d("TEST", "mailAddresses : " + mailAddresses.getValue().toString()  );
        return mailAddresses;
    }

    public LiveData<List<PostalAddress>> getPostalAddresses(Contact contact) {
        if (postalAddresses == null) {
            postalAddresses = new MutableLiveData<>(new ArrayList<>());
            loadPostalAddresses(contact);
        }
        Log.d("TEST", "postalAddresses : " + postalAddresses.getValue().toString()  );
        return postalAddresses;
    }

    public LiveData<List<Phone>> getPhones(Contact contact) {
        if (phones == null) {
            phones = new MutableLiveData<>(new ArrayList<>());
            loadPhones(contact);
        }

        Log.d("TEST", "phones : " + phones.getValue().toString()  );
        return phones;
    }

    public void addMailAddress(Contact contact, String address, String label) {
            sendAddMailAddressRequest(contact, address, label);
    }

    public void addPostalAddress(Contact contact, String address, String city, String country ,String label) {
        sendAddPostalAddressRequest(contact, address, city, country, label);
    }

    public void addPhone(Contact contact, String number, String label) {
        sendAddPhoneRequest(contact, number, label);
    }

    private void sendAddMailAddressRequest(Contact contact, String address, String label) {
        Log.d("TEST", "address: " + address );
        JSONObject json = new JSONObject();
        try{
            json.put("address", address);
            json.put("label", label);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Log.d("JSON", json.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                apiBasename + "/person/" + contact.getId() + "/mailAddress",
                json,
                response -> {
                    try {
                        MailAddress mailAddress = new MailAddress(
                                response.getString("id"),
                                response.getString("address"),
                                response.getString("label")
                        );
                        contact.addMailAddress(mailAddress);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        requestQueue.add(request);
    }

    private void sendAddPostalAddressRequest(Contact contact, String address, String city, String country,String label) {
        Log.d("TEST", "address: " + address );
        JSONObject json = new JSONObject();
        try{
            json.put("address", address);
            json.put("label", label);
            json.put("city", city);
            json.put("country", country);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Log.d("JSON", json.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                apiBasename + "/person/" + contact.getId() + "/postalAddress",
                json,
                response -> {
                    try {
                        PostalAddress postalAddress = new PostalAddress(
                                response.getString("id"),
                                response.getString("address"),
                                response.getString("city"),
                                response.getString("country"),
                                response.getString("label")
                        );
                        contact.addPostalAddress(postalAddress);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        requestQueue.add(request);
    }

    private void sendAddPhoneRequest(Contact contact, String number, String label) {
        Log.d("TEST", "number: " + number );
        JSONObject json = new JSONObject();
        try{
            json.put("number", number);
            json.put("label", label);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Log.d("JSON", json.toString());

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                apiBasename + "/person/" + contact.getId() + "/phone",
                json,
                response -> {
                    try {
                        Phone phone = new Phone(
                                response.getString("id"),
                                response.getString("number"),
                                response.getString("label")
                        );
                        contact.addPhoneNumber(phone);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        requestQueue.add(request);
    }

    private void loadMailAddresses(Contact contact) {

        JsonArrayRequest mailRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/mailAddress",
                response -> {
                    Log.d("TEST", "loadMailAddresses");
                    try {
                        if(response.length() == 0) {
                            Log.d("TEST", "no mail address");
                            return;
                        }
                        List<MailAddress> mailAddresses = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            mailAddresses.add(new MailAddress(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("address"),
                                    jsonObject.getString("label")
                            ));

                        }

                        for (MailAddress mailAddress : mailAddresses) {
                            contact.addMailAddress(mailAddress);
                        }

                        this.mailAddresses.postValue(mailAddresses);


                    } catch (Exception e) {
                        throw new RuntimeException(e);

                    }
                },
                error -> {
                    Log.d("MAIL", error.toString());
                }
        );

        requestQueue.add(mailRequest);
    }

    private void loadPostalAddresses(Contact contact) {
        JsonArrayRequest postalRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/postalAddress",
                response -> {
                    try {
                        List<PostalAddress> postalAddresses = new ArrayList<>();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            postalAddresses.add(new PostalAddress(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("address"),
                                    jsonObject.getString("city"),
                                    jsonObject.getString("country"),
                                    jsonObject.getString("label")
                            ));
                        }
                        for (PostalAddress postalAddress : postalAddresses) {
                            contact.addPostalAddress(postalAddress);
                        }

                        this.postalAddresses.postValue(postalAddresses);


                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        requestQueue.add(postalRequest);

    }

    private void loadPhones(Contact contact) {
        JsonArrayRequest phoneRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/phone",
                response -> {
                    List<Phone> phones = new ArrayList<>();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            phones.add(new Phone(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("number"),
                                    jsonObject.getString("label")
                            ));
                        }

                        for (Phone phone : phones) {
                            contact.addPhoneNumber(phone);
                        }

                        this.phones.postValue(phones);

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> {
                    Log.d("JSON", error.toString());
                }
        );

        requestQueue.add(phoneRequest);
    }

    private void loadGroupsFromContact(Contact contact){
        JsonArrayRequest groupRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/groups",
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

        requestQueue.add(groupRequest);
    }

}
