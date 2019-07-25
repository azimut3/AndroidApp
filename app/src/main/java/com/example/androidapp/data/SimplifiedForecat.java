package com.example.androidapp.data;

import java.util.Objects;

public class SimplifiedForecat {
    private String date;
    private Double minT;
    private Double maxT;
    //todo redo weather state if improving app
    private String weatherState;

    public SimplifiedForecat(){

    }

    public String getDate() {
        return date;
    }

    public SimplifiedForecat setDate(String date) {
        this.date = date;
        return this;
    }

    public Double getMinT() {
        return minT;
    }

    public SimplifiedForecat setMinT(Double minT) {
        this.minT = minT;
        return this;

    }

    public Double getMaxT() {
        return maxT;
    }

    public String getWeatherState() {
        return weatherState;
    }

    public SimplifiedForecat setWeatherState(String weatherState) {
        this.weatherState = weatherState;
        return this;
    }

    public SimplifiedForecat setMaxT(Double maxT) {
        this.maxT = maxT;
        return this;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimplifiedForecat that = (SimplifiedForecat) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date);
    }


}
