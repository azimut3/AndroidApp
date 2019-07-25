
package com.example.androidapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class List {

    /**
     * Time of data forecasted, unix, UTC
     */
    @SerializedName("dt")
    @Expose
    private Integer timeStamp;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private java.util.List<WeatherDescription> weatherDescription = null;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    /**
     * Data/time of calculation, UTC
     */
    @SerializedName("dt_txt")
    @Expose
    private String forecastDate;
    @SerializedName("rain")
    @Expose
    private Rain rain;
    @SerializedName("snow")
    @Expose
    private Snow snow;

    public Integer getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Integer timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public java.util.List<WeatherDescription> getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(java.util.List<WeatherDescription> weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(String forecastDate) {
        this.forecastDate = forecastDate;
    }

    public Rain getRain() {
        return rain;
    }

    public void setRain(Rain rain) {
        this.rain = rain;
    }

    public Snow getSnow() {
        return snow;
    }

    public void setSnow(Snow snow) {
        this.snow = snow;
    }

    @Override
    public String toString() {
        return "List{" +
                "timeStamp=" + timeStamp +
                ", main=" + main +
                ", weatherDescription=" + weatherDescription +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", forecastDate='" + forecastDate + '\'' +
                ", rain=" + rain +
                ", snow=" + snow +
                '}';
    }
}
