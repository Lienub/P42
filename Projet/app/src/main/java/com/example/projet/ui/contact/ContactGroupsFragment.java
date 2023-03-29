package com.example.projet.ui.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.projet.ui.group.GroupFragment;
import com.example.projet.ui.home.HomeViewModel;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.viewmodel.AddressBookViewModel;

import java.util.List;

public class ContactGroupsFragment extends Fragment implements RecyclerViewInterface {
    private FragmentHomeBinding binding;
    private RecyclerView contactRecyclerView;
    private Group group;
    private List<Contact> contacts;
    private ContactAdapter contactAdapter;


    public ContactGroupsFragment(Group group) {
        this.group = group;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contact_groups, container, false);

        super.onViewCreated(view,savedInstanceState);

        //ArrayAdapter<CharSequence> test = ArrayAdapter.



                contactRecyclerView = view.findViewById(R.id.contact_recyclerview);
        Button deleteGroup = view.findViewById(R.id.delete_group);

        AddressBookViewModel model = new ViewModelProvider(this).get(AddressBookViewModel.class);
        model.getContactsFromGroup(group).observe(getViewLifecycleOwner(), contacts -> {
            this.contacts = contacts;
            contactAdapter = new ContactAdapter(contacts, this);
            contactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
            contactRecyclerView.setAdapter(contactAdapter);
            contactAdapter.notifyDataSetChanged();
        });

        deleteGroup.setOnClickListener(v -> {
            model.deleteGroup(group.getId());
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.nav_host_fragment_activity_main, new GroupFragment())
                    .addToBackStack(null)
                    .commit();
        });

        return view;
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
                .setReorderingAllowed(true)
                .replace(R.id.nav_host_fragment_activity_main, contactFragment)
                .addToBackStack(null)
                .commit();
    }

}