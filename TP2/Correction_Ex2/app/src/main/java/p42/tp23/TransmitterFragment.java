package p42.tp23;

import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class TransmitterFragment extends Fragment {

    public TransmitterFragment() {
        super(R.layout.fragment_transmitter);
    }

    @Override
    public void onStop() {
        super.onStop();
        // Save my message for Fragment Two when I'm stopped
        EditText message = getView().findViewById(R.id.editTextMessage);
        TextViewModel textViewModel = new ViewModelProvider(getActivity()).get(TextViewModel.class);
        textViewModel.setText(message.getText().toString());
    }


}