package com.example.projet.ui.group;

import android.location.Address;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.databinding.FragmentGroupBinding;
import com.example.projet.model.Group;
import com.example.projet.ui.contact.ContactGroupsFragment;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.viewmodel.AddressBookViewModel;

import java.util.List;

public class GroupFragment extends Fragment implements RecyclerViewInterface {

        private FragmentGroupBinding binding;

        private List<Group> groups;
        private GroupAdapter groupAdapter;
        private RecyclerView groupRecyclerView;
        AddressBookViewModel addressBookViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_group, container, false);

        groupRecyclerView = view.findViewById(R.id.group_recyclerview);

        groupRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        addressBookViewModel = new ViewModelProvider(this).get(AddressBookViewModel.class);

        addressBookViewModel.getGroups().observe(getViewLifecycleOwner(), groups -> {
            this.groups = groups;
            groupAdapter = new GroupAdapter(groups, this);
            groupRecyclerView.setAdapter(groupAdapter);
            groupAdapter.notifyDataSetChanged();
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
        ContactGroupsFragment contactGroup = new ContactGroupsFragment(groups.get(position));

        getParentFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.nav_host_fragment_activity_main, contactGroup)
                .addToBackStack(null)
                .commit();
    }
}