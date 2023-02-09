package p4a.tp12;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailsActivity extends AppCompatActivity {

    public DetailsActivity() {
        super(R.layout.activity_details);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent startIntent = getIntent();
        Bundle extras = startIntent.getExtras();

        ((TextView)findViewById(R.id.textViewFirstname)).setText(extras.getString(getString(R.string.firstname)));
        ((TextView)findViewById(R.id.textViewLastame)).setText(extras.getString(getString(R.string.lastname)));
        ((TextView)findViewById(R.id.textViewAddress)).setText(extras.getString(getString(R.string.address)));
        ((TextView)findViewById(R.id.textViewPhone)).setText(extras.getString(getString(R.string.phone)));
    }
}
