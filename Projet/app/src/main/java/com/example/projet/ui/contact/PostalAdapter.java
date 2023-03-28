package com.example.projet.ui.contact;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.PostalAddress;
import com.example.projet.view.PostalViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.ArrayList;
import java.util.List;

public class PostalAdapter extends RecyclerView.Adapter<PostalViewHolder> {
    List<PostalAddress> postal_addresses = new ArrayList<>();

    public void setPostalAddresses(List<PostalAddress> postal_addresses) {
        this.postal_addresses = postal_addresses;
    }

    @NonNull
    @Override
    public PostalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.postal_view, parent, false);
        return new PostalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostalViewHolder holder, int position) {
        holder.address.setText(postal_addresses.get(position).getAddress());
        holder.city.setText(postal_addresses.get(position).getCity());
        holder.country.setText(postal_addresses.get(position).getCountry());
        holder.label.setText(postal_addresses.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return postal_addresses.size();
    }
}
