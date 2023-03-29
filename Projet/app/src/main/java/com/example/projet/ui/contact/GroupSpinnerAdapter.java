package com.example.projet.ui.contact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projet.R;

import java.util.ArrayList;
import java.util.List;

public class GroupSpinnerAdapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> values;

    public GroupSpinnerAdapter(Context context, List<String> values) {
        super(context, R.layout.contact_view, values);
        this.context = context;
        this.values = values;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_spinner, null, false);
        TextView textView = (TextView) view.findViewById(R.id.spinner_group_item_text);
        textView.setText(values.get(position));
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.group_spinner_item, null, false);
        TextView textView = (TextView) view.findViewById(R.id.depit);
        textView.setText(values.get(position));
        return view;
    }
}