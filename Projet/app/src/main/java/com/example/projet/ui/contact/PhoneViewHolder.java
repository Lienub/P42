package com.example.projet.ui.contact;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;

public class PhoneViewHolder extends RecyclerView.ViewHolder {

    public TextView number;
    public TextView label;

    public PhoneViewHolder(@NonNull View itemView) {
        super(itemView);
        number = itemView.findViewById(R.id.phone_number);
        label = itemView.findViewById(R.id.phone_label);

    }
}
