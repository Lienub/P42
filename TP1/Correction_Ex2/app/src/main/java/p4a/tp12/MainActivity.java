package p4a.tp12;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button buttonCancel = findViewById(R.id.buttonCancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EditText)findViewById(R.id.editTextFirstname)).setText("");
                ((EditText)findViewById(R.id.editTextLastname)).setText("");
                ((EditText)findViewById(R.id.editTextAddress)).setText("");
                ((EditText)findViewById(R.id.editTextPhone)).setText("");
            }
        });

        Button buttonOk = findViewById(R.id.buttonOk);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), DetailsActivity.class);
                startIntent.putExtra(getString(R.string.firstname),((EditText)findViewById(R.id.editTextFirstname)).getText().toString());
                startIntent.putExtra(getString(R.string.lastname),((EditText)findViewById(R.id.editTextLastname)).getText().toString());
                startIntent.putExtra(getString(R.string.address),((EditText)findViewById(R.id.editTextAddress)).getText().toString());
                startIntent.putExtra(getString(R.string.phone),((EditText)findViewById(R.id.editTextPhone)).getText().toString());
                startActivity(startIntent);
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        ((EditText)findViewById(R.id.editTextFirstname)).setText( prefs.getString(getString(R.string.firstname),"") );
        ((EditText)findViewById(R.id.editTextLastname)).setText( prefs.getString(getString(R.string.lastname),"") );
        ((EditText)findViewById(R.id.editTextAddress)).setText( prefs.getString(getString(R.string.address),"") );
        ((EditText)findViewById(R.id.editTextPhone)).setText( prefs.getString(getString(R.string.phone),"") );
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(getString(R.string.firstname), ((EditText)findViewById(R.id.editTextFirstname)).getText().toString());
        editor.putString(getString(R.string.lastname), ((EditText)findViewById(R.id.editTextLastname)).getText().toString());
        editor.putString(getString(R.string.address), ((EditText)findViewById(R.id.editTextAddress)).getText().toString());
        editor.putString(getString(R.string.phone), ((EditText)findViewById(R.id.editTextPhone)).getText().toString());
        editor.apply();
    }

}
