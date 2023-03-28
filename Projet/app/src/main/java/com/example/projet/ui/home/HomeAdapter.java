package com.example.projet.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.*;
import com.example.projet.view.HomeViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    List<Contact> contacts;
    private final RecyclerViewInterface recyclerViewInterface;

    public HomeAdapter(List<Contact> contacts, RecyclerViewInterface recyclerViewInterface) {
        this.contacts = contacts;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.utme_view, parent, false);
        return new HomeViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        holder.firstname.setText(contacts.get(position).getFirstname());
        holder.lastname.setText(contacts.get(position).getLastname());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }
}
