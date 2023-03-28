package com.example.projet.ui.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.*;
import com.example.projet.view.ContactViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactViewHolder> {
    List<Contact> contacts;
    private final RecyclerViewInterface recyclerViewInterface;

    public ContactAdapter(List<Contact> contacts, RecyclerViewInterface recyclerViewInterface) {
        this.contacts = contacts;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.utme_view, parent, false);
        return new ContactViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        holder.firstname.setText(contacts.get(position).getFirstname());
        holder.lastname.setText(contacts.get(position).getLastname());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
