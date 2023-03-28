package com.example.projet.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Item;
import com.example.projet.R;

public class HomeViewHolder extends RecyclerView.ViewHolder

    {
    public TextView firstname;
    public TextView lastname;
    public HomeViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        lastname = itemView.findViewById(R.id.name);
        firstname = itemView.findViewById(R.id.surname);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos);
                    }

                }
            }});
    }

    void display(Item item){
        lastname.setText(item.getName());
        firstname.setText(item.getsurname());
    }
}
