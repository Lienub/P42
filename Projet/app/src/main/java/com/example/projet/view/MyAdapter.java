package com.example.projet.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.*;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    List<Contact> contacts;
    private final RecyclerViewInterface recyclerViewInterface;

    public MyAdapter(List<Contact> contacts, RecyclerViewInterface recyclerViewInterface) {
        this.contacts = contacts;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.utme_view, parent, false);
        return new MyViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(contacts.get(position).getFirstname());
        holder.surname.setText(contacts.get(position).getLastname());
    }

    @Override
    public int getItemCount() {
        Log.d("TEST", "MyAdapter: " + contacts);
        return contacts.size();
    }
}
