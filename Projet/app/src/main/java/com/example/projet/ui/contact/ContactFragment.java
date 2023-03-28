package com.example.projet.ui.contact;

import android.os.Bundle;
import android.util.Log;
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
import com.example.projet.model.Contact;
import com.example.projet.view.RecyclerViewInterface;
import com.example.projet.viewmodel.InfosViewModel;

public class ContactFragment extends Fragment implements RecyclerViewInterface {
        private RecyclerView mailRecyclerView, phoneRecyclerView, postalRecyclerView;
        private Contact contact;

        private MailAdapter mailAdapter;
        private PhoneAdapter phoneAdapter;
        private PostalAdapter postalAdapter;

        InfosViewModel contactViewModel;

        public ContactFragment(Contact contact) {
            this.contact = contact;
        }

        public View onCreateView(@NonNull LayoutInflater inflater,
                                 ViewGroup container, Bundle savedInstanceState) {

            View view = inflater.inflate(R.layout.fragment_contact, container, false);

            mailRecyclerView = view.findViewById(R.id.recycler_mail);
            phoneRecyclerView = view.findViewById(R.id.recycler_phone);
            postalRecyclerView = view.findViewById(R.id.recycler_post);

            phoneAdapter = new PhoneAdapter();
            mailAdapter = new MailAdapter();
            postalAdapter = new PostalAdapter();

            mailRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            phoneRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            postalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            mailRecyclerView.setAdapter(mailAdapter);
            phoneRecyclerView.setAdapter(phoneAdapter);
            postalRecyclerView.setAdapter(postalAdapter);

            contactViewModel = new ViewModelProvider(this).get(InfosViewModel.class);

            contactViewModel.getMailAddresses(contact).observe(getViewLifecycleOwner(), mailAddresses -> {
                Log.d("TEST", "onCreateView: " + contact);
                Log.d("TEST", "onCreateView: " + mailAddresses);
                mailAdapter.setMailAddresses(mailAddresses);
            });

            contactViewModel.getPhones(contact).observe(getViewLifecycleOwner(), phoneNumbers -> {
                phoneAdapter.setPhones(phoneNumbers);
            });

            contactViewModel.getPostalAddresses(contact).observe(getViewLifecycleOwner(), postalAddresses -> {
                postalAdapter.setPostalAddresses(postalAddresses);
            });

            return view;
        }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        com.example.projet.databinding.FragmentContactBinding binding = null;
    }


    @Override
    public void onItemClick(int position) {

    }
}
