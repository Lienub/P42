package com.example.projet.ui.contact.add;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.projet.R;
import com.example.projet.model.Contact;
import com.example.projet.ui.contact.ContactFragment;
import com.example.projet.viewmodel.InfosViewModel;

public class AddPhoneFragment extends Fragment {
    private String number;
    private String label;

    private final Contact contact;
    private InfosViewModel viewModel;

    public AddPhoneFragment(Contact contact) {
        this.contact = contact;
    }

    public AddPhoneFragment() {
        this.contact = null;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.add_phone_view, container, false);

        Spinner spinner = (Spinner) view.findViewById(R.id.phone_label_list);
        EditText input = (EditText) view.findViewById(R.id.phone_number_input);
        Button submit = (Button) view.findViewById(R.id.phone_confirm);

        viewModel = new InfosViewModel();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.label, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = input.getText().toString();
                label = spinner.getSelectedItem().toString();
                viewModel.addPhone(contact, number, label);
                Log.d("AddPhoneFragment", "Contact updated");
                ContactFragment contactFragment = new ContactFragment(contact);

                getParentFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_activity_main, contactFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        com.example.projet.databinding.FragmentContactBinding binding = null;
    }
}
