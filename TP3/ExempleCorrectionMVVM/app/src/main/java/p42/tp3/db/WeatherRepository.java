package p42.tp3.db;

import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import p42.tp3.VolleyRequestQueue;
import p42.tp3.model.Weather;

public class WeatherRepository {

    private final String API_URL = "https://api.openweathermap.org/data/2.5/weather?units=metric&lang=fr";
    // TOD0: Insert your OpenWeatherMap API key here
    private final String API_KEY = "ec14e7d77783e2254079a50e80fe343c";

    private MutableLiveData<Weather> _weather = new MutableLiveData<>();

    public WeatherRepository() {}

    public LiveData<Weather> getWeather() {
        return _weather;
    }

    // Requête Volley pour obtenir la météo de la ville en paramètre
    public void findWeather(String city) {
        JsonObjectRequest jsonRequest = new JsonObjectRequest(
            API_URL+"&APPID="+API_KEY+"&q="+city,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject data) {
                    // Lorsqu'on obtient la réponse de l'API, on modifie l'attribut de classe correspondant
                    try {
                        Weather weather = new Weather(
                            data.getJSONObject("main").getInt("temp"),
                            data.getJSONObject("main").getInt("humidity"),
                            data.getJSONObject("wind").getInt("speed"),
                            data.getJSONObject("clouds").getInt("all"),
                            data.getJSONArray("weather").getJSONObject(0).getString("description")
                        );
                        // On modifie la météo puis on lance la requête pour obtenir l'icône
                        _weather.setValue(weather);
                        requestIcon(data.getJSONArray("weather").getJSONObject(0).getString("icon"));
                    }
                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Request weather", "Volley error");
                }
        });
        VolleyRequestQueue.getInstance().add(jsonRequest);
    }

    private void requestIcon( String id ) {
        ImageRequest imageRequest = new ImageRequest(
            "https://openweathermap.org/img/w/"+id+".png",
            new Response.Listener<Bitmap>() {
                @Override
                public void onResponse(Bitmap icon) {
                    Weather weather = _weather.getValue();
                    weather.setIcon( icon );
                    _weather.setValue(weather);
                }
            },
            0, 0, ImageView.ScaleType.FIT_CENTER, Bitmap.Config.ARGB_4444,
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("Icon Loading","Volley error.");
                }
        });

        VolleyRequestQueue.getInstance().add(imageRequest);
    }

}
