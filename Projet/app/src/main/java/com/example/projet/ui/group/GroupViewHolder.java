package com.example.projet.ui.group;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.Item;
import com.example.projet.R;
import com.example.projet.view.RecyclerViewInterface;

public class GroupViewHolder extends RecyclerView.ViewHolder

    {
    public TextView title;
    public GroupViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
        super(itemView);
        title = itemView.findViewById(R.id.group_title);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (recyclerViewInterface != null) {
                    int pos = getAdapterPosition();
                    int type = 2;

                    if (pos != RecyclerView.NO_POSITION) {
                        recyclerViewInterface.onItemClick(pos, type);
                    }

                }
            }});
    }

    void display(Item item){
        title.setText(item.getName());
    }
}
