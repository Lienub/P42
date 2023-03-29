package com.example.projet.ui.group;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projet.R;
import com.example.projet.viewmodel.AddressBookViewModel;

public class AddGroupFragment extends Fragment {

    private AddressBookViewModel viewModel;
    private Fragment parent;

    public AddGroupFragment(Fragment fragment) {
        parent = fragment;
    }
    public AddGroupFragment() {
        parent = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_group_view, container, false);

        viewModel = new AddressBookViewModel();

        EditText title = view.findViewById(R.id.group_title_input);
        Button submit = view.findViewById(R.id.group_confirm);

        submit.setOnClickListener(v -> {
            viewModel.addGroup(title.getText().toString());
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
