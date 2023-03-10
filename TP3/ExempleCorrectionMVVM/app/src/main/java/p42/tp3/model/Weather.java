package p42.tp3.model;

import android.graphics.Bitmap;

public class Weather {

    private Integer _temperature = null;
    private Integer _humidity = null;
    private Integer _windSpeed = null;
    private Integer _cloudPercentage = null;
    private Bitmap _icon = null;
    private String _description = null;

    public Weather( Integer temp, Integer humidity, Integer windSpeed, Integer cloudPercentage, String description ) {
        _temperature = temp;
        _humidity = humidity;
        _windSpeed = windSpeed;
        _cloudPercentage = cloudPercentage;
        _description = description;
    }

    public Integer getTemperature() {
        return _temperature;
    }

    public void setTemperature(Integer temperature) {
        _temperature = temperature;
    }

    public Integer getHumidity() {
        return _humidity;
    }

    public void setHumidity(Integer humidity) {
        _humidity = humidity;
    }

    public Integer getWindSpeed() {
        return _windSpeed;
    }

    public void setWindSpeed(Integer windSpeed) {
        _windSpeed = windSpeed;
    }

    public Integer getCloudPercentage() {
        return _cloudPercentage;
    }

    public void setCloudPercentage(Integer cloudPercentage) {
        _cloudPercentage = cloudPercentage;
    }

    public Bitmap getIcon() {
        return _icon;
    }

    public void setIcon(Bitmap icon) {
        _icon = icon;
    }

    public String geDescription() {
        return _description;
    }

    public void seDescription(String description) {
        _description = description;
    }
}
