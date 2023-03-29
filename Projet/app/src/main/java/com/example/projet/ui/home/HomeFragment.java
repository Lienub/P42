package com.example.projet.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.ui.contact.ContactAdapter;
import com.example.projet.ui.contact.ContactFragment;
import com.example.projet.R;
import com.example.projet.ui.contact.add.AddContactFragment;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.databinding.FragmentHomeBinding;
import com.example.projet.model.*;
import com.example.projet.viewmodel.*;

import java.util.List;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    private FragmentHomeBinding binding;
    private RecyclerView contactRecyclerView;
    private List<Contact> contacts;
    private ContactAdapter contactAdapter;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding = FragmentHomeBinding.inflate(getLayoutInflater());

        View root = binding.getRoot();
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view,savedInstanceState);

        contactRecyclerView = view.findViewById(R.id.contact_recyclerview);

        Button addContact = view.findViewById(R.id.add_contact);

        AddressBookViewModel model = new ViewModelProvider(this).get(AddressBookViewModel.class);
        model.getContacts().observe(getViewLifecycleOwner(), contacts -> {
            this.contacts = contacts;
            Log.d("TEST", "onViewCreated: " + contacts);
            contactAdapter = new ContactAdapter(contacts, this);
            contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            contactRecyclerView.setAdapter(contactAdapter);
            contactAdapter.notifyDataSetChanged();
        });

        addContact.setOnClickListener(v -> {
            AddContactFragment addContactFragment = new AddContactFragment(this);
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.nav_host_fragment_activity_main, addContactFragment)
                    .addToBackStack(null)
                    .commit();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, int type) {
        if (type == 1){
            ContactFragment contactFragment = new ContactFragment(contacts.get(position));

            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.nav_host_fragment_activity_main, contactFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }

}