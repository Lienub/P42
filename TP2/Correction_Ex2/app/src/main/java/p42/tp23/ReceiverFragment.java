package p42.tp23;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ReceiverFragment extends Fragment {

    public ReceiverFragment() {
        super(R.layout.fragment_receiver);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextViewModel homeViewModel = new ViewModelProvider(getActivity()).get(TextViewModel.class);
        final TextView textView = view.findViewById(R.id.textMessage);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
    }
}