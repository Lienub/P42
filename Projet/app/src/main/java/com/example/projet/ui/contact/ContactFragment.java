package com.example.projet.ui.contact;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projet.R;
import com.example.projet.model.Contact;
import com.example.projet.model.Group;
import com.example.projet.ui.contact.add.AddMailFragment;
import com.example.projet.ui.contact.add.AddPhoneFragment;
import com.example.projet.ui.contact.add.AddPostalFragment;
import com.example.projet.ui.group.GroupAdapter;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.viewmodel.InfosViewModel;

import java.util.List;

public class ContactFragment extends Fragment implements RecyclerViewInterface {
        private TextView name;
        private RecyclerView mailRecyclerView, phoneRecyclerView, postalRecyclerView, groupRecyclerView;
        private Contact contact;
        private List<Group> groups;

        private MailAdapter mailAdapter;
        private PhoneAdapter phoneAdapter;
        private PostalAdapter postalAdapter;
        private GroupAdapter groupAdapter;

        InfosViewModel contactViewModel;

        public ContactFragment(Contact contact) {
            this.contact = contact;
        }

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_contact, container, false);

            name = view.findViewById(R.id.contact_name);
            name.setText(contact.getFirstname()+" "+contact.getLastname());

            mailRecyclerView = view.findViewById(R.id.recycler_mail);
            phoneRecyclerView = view.findViewById(R.id.recycler_phone);
            postalRecyclerView = view.findViewById(R.id.recycler_post);
            groupRecyclerView = view.findViewById(R.id.recycler_group);

            Button addMail = view.findViewById(R.id.add_mail);
            Button addPostal = view.findViewById(R.id.add_post);
            Button addPhone = view.findViewById(R.id.add_phone);

            mailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            phoneRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            postalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            groupRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            contactViewModel = new ViewModelProvider(this).get(InfosViewModel.class);

            contactViewModel.getMailAddresses(contact).observe(getViewLifecycleOwner(), mailAddresses -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + mailAddresses);
                mailAdapter = new MailAdapter(mailAddresses);
                mailRecyclerView.setAdapter(mailAdapter);
                mailAdapter.notifyDataSetChanged();
            });

            contactViewModel.getPhones(contact).observe(getViewLifecycleOwner(), phoneNumbers -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + phoneNumbers);
                phoneAdapter = new PhoneAdapter(phoneNumbers);
                phoneRecyclerView.setAdapter(phoneAdapter);
                phoneAdapter.notifyDataSetChanged();
            });

            contactViewModel.getPostalAddresses(contact).observe(getViewLifecycleOwner(), postalAddresses -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + postalAddresses);
                postalAdapter = new PostalAdapter(postalAddresses);
                postalRecyclerView.setAdapter(postalAdapter);
                postalAdapter.notifyDataSetChanged();
            });

            contactViewModel.getGroupsFromContact(contact).observe(getViewLifecycleOwner(), groups -> {
                this.groups = groups;
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + groups);
                groupAdapter = new GroupAdapter(groups, this);
                groupRecyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();
            });

            addMail.setOnClickListener(v -> {
                AddMailFragment addMailFragment = new AddMailFragment(contact);

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.nav_host_fragment_activity_main, addMailFragment)
                        .addToBackStack(null)
                        .commit();
            });

            addPostal.setOnClickListener(v -> {
                AddPostalFragment addPostalFragment = new AddPostalFragment(contact);

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.nav_host_fragment_activity_main, addPostalFragment)
                        .addToBackStack(null)
                        .commit();
            });

            addPhone.setOnClickListener(v -> {
                AddPhoneFragment addPhoneFragment = new AddPhoneFragment(contact);

                getParentFragmentManager()
                        .beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.nav_host_fragment_activity_main, addPhoneFragment)
                        .addToBackStack(null)
                        .commit();
            });

            return view;
        }

        public void onResume() {
            super.onResume();
            contactViewModel.getMailAddresses(contact).observe(getViewLifecycleOwner(), mailAddresses -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + mailAddresses);
                mailAdapter = new MailAdapter(mailAddresses);
                mailRecyclerView.setAdapter(mailAdapter);
                mailAdapter.notifyDataSetChanged();
            });

            contactViewModel.getPhones(contact).observe(getViewLifecycleOwner(), phoneNumbers -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + phoneNumbers);
                phoneAdapter = new PhoneAdapter(phoneNumbers);
                phoneRecyclerView.setAdapter(phoneAdapter);
                phoneAdapter.notifyDataSetChanged();
            });

            contactViewModel.getPostalAddresses(contact).observe(getViewLifecycleOwner(), postalAddresses -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + postalAddresses);
                postalAdapter = new PostalAdapter(postalAddresses);
                postalRecyclerView.setAdapter(postalAdapter);
                postalAdapter.notifyDataSetChanged();
            });

            contactViewModel.getGroupsFromContact(contact).observe(getViewLifecycleOwner(), groups -> {
                this.groups = groups;
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + groups);
                groupAdapter = new GroupAdapter(groups, this);
                groupRecyclerView.setAdapter(groupAdapter);
                groupAdapter.notifyDataSetChanged();
            });
        }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        com.example.projet.databinding.FragmentContactBinding binding = null;
    }


    @Override
    public void onItemClick(int position, int type) {
        if (type == 2){
            ContactGroupsFragment contactGroupsFragment = new ContactGroupsFragment(groups.get(position));

            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.nav_host_fragment_activity_main, contactGroupsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
