
package com.example.androidapp.data;

import com.example.androidapp.managers.ComplexForecast;
import com.example.androidapp.managers.SimplifiedForecat;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
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

    private static java.util.List<SimplifiedForecat> datesAndTemperatures = new ArrayList<>();
    private static java.util.TreeMap<String, java.util.List<ComplexForecast>> complexForecasts = new TreeMap<>();

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
        if (datesAndTemperatures.size()<1) setupDaysAndTemperatures();
        return datesAndTemperatures;
    }

    private java.util.List<SimplifiedForecat> setupDaysAndTemperatures(){
        SimplifiedForecat temForecast;
        SimplifiedForecat simplifiedForecat;
        for (List forecast : miniForecasts){
            temForecast = new SimplifiedForecat();
            temForecast.setDate(forecast.getForecastDate())
                    .setMinT(forecast.getMain().getTempMin())
                    .setMaxT(forecast.getMain().getTempMin())
                    .setWeatherState(forecast.getWeatherDescription().get(0).getDescription());
            if (datesAndTemperatures.contains(temForecast)){
                simplifiedForecat = datesAndTemperatures.get(datesAndTemperatures
                        .indexOf(temForecast));
                System.out.println(simplifiedForecat + "=================");
                if (simplifiedForecat.getMinT()>temForecast.getMinT()) simplifiedForecat
                        .setMinT(temForecast.getMinT());
                if (simplifiedForecat.getMaxT()>temForecast.getMaxT()) simplifiedForecat
                        .setMaxT(temForecast.getMaxT());
                simplifiedForecat.setWeatherState(temForecast.getWeatherState());
            } else {
                datesAndTemperatures.add(temForecast);
            }
        }
        //System.out.println(datesAndTemperatures);
        return datesAndTemperatures;
    }

    private void setupComplexForecasts(){
        for(List forecast : miniForecasts) {
            ComplexForecast complexForecast = new ComplexForecast(
                    forecast.getForecastDate(),
                    forecast.getWeatherDescription().get(0).getDescription(),
                    forecast.getMain().getTempMin(),
                    forecast.getMain().getTempMax(),
                    forecast.getWind().getSpeed(),
                    forecast.getWind().getDeg(),
                    forecast.getMain().getHumidity(),
                    forecast.getMain().getPressure()
            );
            if (complexForecasts.containsKey(complexForecast.getShortDate())) {
                complexForecasts.get(complexForecast.getShortDate()).add(complexForecast);
            } else {
                complexForecasts.put(complexForecast.getShortDate(),
                        new ArrayList<>(Arrays.asList(complexForecast)));
            }
        }
    }

    public java.util.List<ComplexForecast> getComplexForecasts(String shortDate) {
        if (complexForecasts.size()<1) setupComplexForecasts();
        return complexForecasts.get(shortDate);
    }

}
