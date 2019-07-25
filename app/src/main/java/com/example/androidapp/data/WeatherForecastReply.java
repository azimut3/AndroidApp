
package com.example.androidapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class WeatherForecastReply {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("message")
    @Expose
    private Double message;
    /**
     *  Number of lines returned by this API call
     */
    @SerializedName("cnt")
    @Expose
    private Integer repliesQtt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> miniForecasts = null;
    @SerializedName("city")
    @Expose
    private City city;

    private java.util.List<SimplifiedForecat> datesAndTemperatures = new ArrayList<>();

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getMessage() {
        return message;
    }

    public void setMessage(Double message) {
        this.message = message;
    }

    public Integer getRepliesQtt() {
        return repliesQtt;
    }

    public void setRepliesQtt(Integer repliesQtt) {
        this.repliesQtt = repliesQtt;
    }

    public java.util.List<List> getMiniForecasts() {
        return miniForecasts;
    }

    public void setMiniForecasts(java.util.List<List> miniForecasts) {
        this.miniForecasts = miniForecasts;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public java.util.List<SimplifiedForecat> getDatesAndTemperatures() {
        return datesAndTemperatures;
    }

    public void setupDaysAndTemperatures(){
        SimplifiedForecat temForecast = new SimplifiedForecat();
        SimplifiedForecat simplifiedForecat;
        for (List forecat : miniForecasts){
            temForecast.setDate(forecat.getDateTimeOfCalculation())
                    .setMinT(forecat.getMain().getTempMin())
                    .setMaxT(forecat.getMain().getTempMin())
                    .setWeatherState(forecat.getWeatherDescription().get(0).getDescription());
            if (datesAndTemperatures.contains(temForecast)){
                simplifiedForecat = datesAndTemperatures.get(datesAndTemperatures
                        .indexOf(temForecast));
                if (simplifiedForecat.getMinT()>temForecast.getMinT()) simplifiedForecat
                        .setMinT(temForecast.getMinT());
                if (simplifiedForecat.getMaxT()>temForecast.getMaxT()) simplifiedForecat
                        .setMaxT(temForecast.getMaxT());
                simplifiedForecat.setWeatherState(temForecast.getWeatherState());
            } else {
                datesAndTemperatures.add(temForecast);
            }
        }

    }

}
