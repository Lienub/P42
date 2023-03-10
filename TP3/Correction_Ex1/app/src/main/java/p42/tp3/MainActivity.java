package p42.tp3;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lang=fr";

    // TOD0: Insert your OpenWeatherMap API key here
    private final String API_KEY = "ec14e7d77783e2254079a50e80fe343c";

    // Queue for all requests using Volley
    private RequestQueue _reqQueue = null;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        _reqQueue = Volley.newRequestQueue(this );

        Button button = findViewById(R.id.buttonValidate);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                requestWeather();
            }
        });
    }

    private void requestWeather() {
        EditText city = findViewById(R.id.editChoosenCity);
        String url = API_URL+"&APPID="+API_KEY+"&q="+city.getText().toString();
        JsonObjectRequest jsonRequest = new JsonObjectRequest(url,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    TextView weatherText = (TextView) findViewById(R.id.weatherText);
                    weatherText.setText(response.toString());
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Request weather", "Volley error");
                    TextView weatherText = (TextView) findViewById(R.id.weatherText);
                    weatherText.setText(error.toString());
                }
        });
        _reqQueue.add(jsonRequest);
    }

}
