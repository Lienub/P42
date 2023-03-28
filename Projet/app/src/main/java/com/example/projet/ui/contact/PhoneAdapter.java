package com.example.projet.ui.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.Phone;
import com.example.projet.view.PhoneViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.List;

public class PhoneAdapter extends RecyclerView.Adapter<PhoneViewHolder> {
    List<Phone> phones;
    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }
    @NonNull
    @Override
    public PhoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.phone_view, parent, false);
        return new PhoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhoneViewHolder holder, int position) {
        holder.number.setText(phones.get(position).getNumber());
        holder.label.setText(phones.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return phones.size();
    }
}
