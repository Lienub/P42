package com.example.projet.ui.home;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.example.projet.view.MyAdapter;
import com.example.projet.R;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.databinding.FragmentHomeBinding;
import com.example.projet.model.*;
import com.example.projet.viewmodel.*;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    RequestQueue instance = null;
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private List<Contact> contacts;
    private MyAdapter adapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST", "onCreateView: ");
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        final TextView textView = binding.textHome;
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);
        Log.d("TEST", "onViewCreated: ");

        recyclerView = view.findViewById(R.id.recyclerview);
        AddressBookViewModel model = new ViewModelProvider(this).get(AddressBookViewModel.class);
        model.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            Log.d("TEST", "onViewCreated: contacts : " + contacts);
            adapter = new MyAdapter(contacts, this);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Coucou Julien");
        builder.setCancelable(false);

        AlertDialog alert = builder.create();
        alert.show();
    }
}