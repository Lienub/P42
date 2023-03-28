package com.example.projet.ui.contact;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Item;
import com.example.projet.R;
import com.example.projet.view.RecyclerViewInterface;

public class ContactViewHolder extends RecyclerView.ViewHolder

    {
    public TextView firstname;
    public TextView lastname;
    public ContactViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        lastname = itemView.findViewById(R.id.name);
        firstname = itemView.findViewById(R.id.surname);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    int type = 1;

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos, type);
                    }

                }
            }});
    }

    void display(Item item){
        lastname.setText(item.getName());
        firstname.setText(item.getsurname());
    }
}
