package p42.tp3.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import p42.tp3.db.WeatherRepository;
import p42.tp3.model.Weather;

/**********************************************************************
 *  Class WeatherViewModel
 *
 *  Use an
 */
public class WeatherViewModel extends ViewModel {

    //******************************************************************
    // Attributs pour stocker les données de ce ViewModel
    //******************************************************************
    private final WeatherRepository _repository;
    private final MutableLiveData<String> _city = new MutableLiveData<String>();

    public WeatherViewModel() {
        _repository = new WeatherRepository();
    }

    public LiveData<String> getCity() {
        return _city;
    }

    public void setCity(String city) {
        _city.setValue(city);
    // When city is modified, call to the repository to find weather of this city.
        _repository.findWeather(city);
    }

    public LiveData<Weather> getWeather() { return _repository.getWeather(); }

}

