package com.example.projet.view;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Item;
import com.example.projet.R;

public class MyViewHolder extends RecyclerView.ViewHolder

    {
    TextView surname;
    TextView name;
    public MyViewHolder(@NonNull View itemView, RecyclerViewInterface recycclerViewInterface) {
        super(itemView);
        name = itemView.findViewById(R.id.name);
        surname = itemView.findViewById(R.id.surname);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recycclerViewInterface != null) {
                    int pos = getAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION) {
                        recycclerViewInterface.onItemClick(pos);
                    }

                }
            }});
    }

    void display(Item item){
        name.setText(item.getName());
        surname.setText(item.getsurname());
    }
}
