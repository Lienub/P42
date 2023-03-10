package p42.tp3;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lang=fr";

    // TOD0: Insert your OpenWeatherMap API key here
    private final String API_KEY = "ec14e7d77783e2254079a50e80fe343c";

    // Queue for all requests using Volley
    private RequestQueue _reqQueue = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    processWeatherJson(response);
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Request weather", "Volley error");
                }
        });
        _reqQueue.add(jsonRequest);
    }

    @SuppressLint("SetTextI18n")
    private void processWeatherJson(JSONObject json ) {
        try {
            ((TextView) findViewById(R.id.weatherDescription)).setText(
                // json.weather[0].description
                json.getJSONArray("weather").getJSONObject(0).getString("description")
            );
            ((TextView) findViewById(R.id.temperatureActual)).setText(
                json.getJSONObject("main").getString("temp")+"°C"
            );
            ((TextView) findViewById(R.id.humidity)).setText(
                json.getJSONObject("main").getString("humidity")+"%"
            );
            ((TextView) findViewById(R.id.windSpeed)).setText(
                json.getJSONObject("wind").getString("speed")+"m/s"
            );
            ((TextView) findViewById(R.id.clouds)).setText(
                json.getJSONObject("clouds").getString("all")+"%"
            );
            requestIconId(json.getJSONArray("weather").getJSONObject(0).getString("icon"));
        }
        catch ( JSONException exception ) {
            Log.d("Weather request", "Volley error");
        }
    }

    private void requestIconId( String id ) {
        String url = "https://openweathermap.org/img/w/"+id+".png";
        ImageRequest imageRequest = new ImageRequest(
                url,
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        processIcon(response);
                    }
                },
                0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_4444,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Icon Loading","Volley error.");
                }
        });

        _reqQueue.add(imageRequest);
    }

    private void processIcon( Bitmap icon ) {
        ImageView imageView = findViewById(R.id.weatherIcon);
        imageView.setImageBitmap(icon);
    }

}
