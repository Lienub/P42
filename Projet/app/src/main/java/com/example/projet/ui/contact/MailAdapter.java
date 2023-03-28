package com.example.projet.ui.contact;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.MailAddress;
import com.example.projet.view.MailViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.List;

public class MailAdapter extends RecyclerView.Adapter<MailViewHolder> {
    List<MailAddress> mail_addresses;

    public void setMailAddresses(List<MailAddress> mail_addresses) {
        this.mail_addresses = mail_addresses;
    }
    @NonNull
    @Override
    public MailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mail_view, parent, false);
        return new MailViewHolder(view);
    }

    @Override
        public void onBindViewHolder(@NonNull MailViewHolder holder, int position) {
        holder.address.setText(mail_addresses.get(position).getAddress());
        holder.label.setText(mail_addresses.get(position).getLabel());
    }

    @Override
    public int getItemCount() {
        return mail_addresses.size();
    }
}
