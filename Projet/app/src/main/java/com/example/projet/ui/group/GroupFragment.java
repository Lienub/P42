package com.example.projet.ui.group;

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

import com.example.projet.R;
import com.example.projet.databinding.FragmentHomeBinding;
import com.example.projet.model.Contact;
import com.example.projet.model.Group;
import com.example.projet.ui.contact.ContactAdapter;
import com.example.projet.ui.contact.ContactFragment;
import com.example.projet.ui.home.HomeViewModel;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.viewmodel.AddressBookViewModel;

import java.util.List;

public class GroupFragment extends Fragment implements RecyclerViewInterface {
    private FragmentHomeBinding binding;
    private RecyclerView contactRecyclerView;
    private Group group;
    private List<Contact> contacts;
    private ContactAdapter contactAdapter;


    public GroupFragment(Group group) {
        this.group = group;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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

        contactRecyclerView = view.findViewById(R.id.contact_recyclerview);

        AddressBookViewModel model = new ViewModelProvider(this).get(AddressBookViewModel.class);
        model.getContactsFromGroup(group).observe(getViewLifecycleOwner(), contacts -> {
            this.contacts = contacts;
            contactAdapter = new ContactAdapter(contacts, this);
            contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            contactRecyclerView.setAdapter(contactAdapter);
            contactAdapter.notifyDataSetChanged();
        });
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position, int type) {
        ContactFragment contactFragment = new ContactFragment(contacts.get(position));

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.nav_host_fragment_activity_main, contactFragment)
                .addToBackStack(null)
                .commit();
    }

}