package com.example.projet.ui.group;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.Group;
import com.example.projet.ui.group.GroupViewHolder;
import com.example.projet.view.RecyclerViewInterface;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupViewHolder> {
    List<Group> groups;
    private final RecyclerViewInterface recyclerViewInterface;

    public GroupAdapter(List<Group> groups, RecyclerViewInterface recyclerViewInterface) {
        this.groups = groups;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.group_view, parent, false);
        return new GroupViewHolder(view, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int position) {
        holder.title.setText(groups.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }
}
