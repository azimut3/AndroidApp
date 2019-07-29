package com.example.androidapp.api;


import com.example.androidapp.data.entities.WeatherForecastReply;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    /*private static final String URL_FORECAST = "http://api.openweathermap.org/data/2.5/forecast?";
    private static final String ID = "id=698740";
    private static final String API_KEY = "&APPID=" + SecretData.weatherKey;
    private static final String UNITS = "&units=metric";
    private static final String LANG = "&lang=ru";
    private static final String MODE = "&mode=xml";*/
// http://api.openweathermap.org/data/2.5/forecast?id=698740&APPID=fe901b4ce965424b15397e529b828d91&units=metric&lang=eng&mode=json
    @GET("/data/2.5/forecast?id=698740&APPID=fe901b4ce965424b15397e529b828d91&units=metric&lang=eng&mode=json")
    Call<WeatherForecastReply> getForecast(@Query("q") String query);


}
