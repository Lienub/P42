package com.example.projet.ui.contact.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projet.R;
import com.example.projet.databinding.FragmentContactBinding;
import com.example.projet.viewmodel.AddressBookViewModel;

public class AddContactFragment extends Fragment {

    private AddressBookViewModel viewModel;
    private final Fragment parent;

    public AddContactFragment(Fragment fragment) {
        parent = fragment;
    }

    public AddContactFragment() {
        parent = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_contact_view, container, false);

        viewModel = new AddressBookViewModel();

        EditText firstname = view.findViewById(R.id.contact_firstname_input);
        EditText lastname = view.findViewById(R.id.contact_lastname_input);
        Button submit = view.findViewById(R.id.contact_confirm);

        submit.setOnClickListener(v -> {
            viewModel.addContact(firstname.getText().toString(), lastname.getText().toString());
            getParentFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.nav_host_fragment_activity_main, parent)
                    .addToBackStack(null)
                    .commit();
        });


        return view;
    }
}
