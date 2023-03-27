package com.example.projet.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.projet.model.*;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;

public class AddressBookViewModel extends ViewModel {
    private static final String apiBasename = "http://localhost:3000";

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
            //loadGroups();
        }
        return groups;
    }

    private void loadContacts() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                apiBasename + "/person",
        response -> {
            try {
                JSONArray jsArr = new JSONArray(response);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
        error -> {
            Log.d("JSON", error.toString());
        }
        );

        instance.add(jsonObjectRequest);
    }

//    private void loadGroups() {
//        // Do an asynchronous operation to fetch groups.
//    }
}
