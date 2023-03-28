package com.example.projet.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Item;
import com.example.projet.R;

public class MailViewHolder extends RecyclerView.ViewHolder {

    public TextView address;
    public TextView label;

    public MailViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.mail_address);
        label = itemView.findViewById(R.id.mail_label);

    }
}
