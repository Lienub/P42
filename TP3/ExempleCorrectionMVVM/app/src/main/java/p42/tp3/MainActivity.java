package p42.tp3;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import p42.tp3.model.Weather;
import p42.tp3.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel _weatherData;

    public MainActivity() {
        super(R.layout.activity_main);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        VolleyRequestQueue.getInstance(this);
        _weatherData = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WeatherViewModel.class);

        // Modification de la ville du ViewModel lors d'un clic sur le bouton de validation.
        findViewById(R.id.buttonValidate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText city = findViewById(R.id.editChosenCity);
                _weatherData.setCity(city.getText().toString());
            }
        });

        // On observe la donnée retournée par getWeather() du ViewModel : lorsqu'elle est modifiée,
        // on met à jour le contenu du TextView correspondant.
        _weatherData.getWeather().observe(this, new Observer<Weather>() {
            @Override
            public void onChanged(Weather weather) {
                ((TextView) findViewById(R.id.weatherDescription)).setText(weather.geDescription());
                ((TextView) findViewById(R.id.temperatureActual)).setText(weather.getTemperature()+"°C");
                ((TextView) findViewById(R.id.humidity)).setText(weather.getHumidity()+"%");
                ((TextView) findViewById(R.id.windSpeed)).setText(weather.getWindSpeed()+"m/s");
                ((TextView) findViewById(R.id.clouds)).setText(weather.getCloudPercentage()+"%");
                ((ImageView) findViewById(R.id.weatherIcon)).setImageBitmap(weather.getIcon());
            }
        });

    }

}
