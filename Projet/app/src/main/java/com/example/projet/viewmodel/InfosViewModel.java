package com.example.projet.viewmodel;

import static com.example.projet.MainActivity.getContext;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet.MainActivity;
import com.example.projet.model.Contact;
import com.example.projet.model.MailAddress;
import com.example.projet.model.Phone;
import com.example.projet.model.PostalAddress;

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

    public InfosViewModel() {
        mailAddresses = new MutableLiveData<>(new ArrayList<>());
        postalAddresses = new MutableLiveData<>(new ArrayList<>());
        phones = new MutableLiveData<>(new ArrayList<>());
    }
    public LiveData<List<MailAddress>> getMailAddresses(Contact contact) {
        if (mailAddresses == null) {
            mailAddresses = new MutableLiveData<>();
            loadMailAddresses(contact);
        }

        Log.d("TEST", "mailAddresses : " + mailAddresses.getValue().toString()  );
        return mailAddresses;
    }

    public LiveData<List<PostalAddress>> getPostalAddresses(Contact contact) {
        if (postalAddresses == null) {
            postalAddresses = new MutableLiveData<>();
            loadPostalAddresses(contact);
        }
        return postalAddresses;
    }

    public LiveData<List<Phone>> getPhones(Contact contact) {
        if (phones == null) {
            phones = new MutableLiveData<>();
            loadPhones(contact);
        }
        return phones;
    }
    private void loadMailAddresses(Contact contact) {
        JsonArrayRequest mailRequest = new JsonArrayRequest(
                apiBasename + "/person/" + contact.getId() + "/mailAddress",
                response -> {
                    try {
                        if(response.length() == 0) {
                            Log.d("TEST", "no contacts");
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
                            contact.addPostalAddress(new PostalAddress(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("address"),
                                    jsonObject.getString("city"),
                                    jsonObject.getString("country"),
                                    jsonObject.getString("label")
                            ));
                        }
                        postalAddresses = contact.getPostalAddresses();
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
                            contact.addPhoneNumber(new Phone(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("number"),
                                    jsonObject.getString("label")
                            ));
                        }
                        phones = contact.getPhoneNumbers();
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

}
