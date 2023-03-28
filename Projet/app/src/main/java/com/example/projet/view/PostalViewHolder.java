package com.example.projet.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;

public class PostalViewHolder extends RecyclerView.ViewHolder {
    public TextView address;
    public TextView city;
    public TextView country;
    public TextView label;
    public PostalViewHolder(@NonNull View itemView) {
        super(itemView);
        address = itemView.findViewById(R.id.postal_address);
        city = itemView.findViewById(R.id.postal_city);
        country = itemView.findViewById(R.id.postal_country);
        label = itemView.findViewById(R.id.postal_label);

    }
}
