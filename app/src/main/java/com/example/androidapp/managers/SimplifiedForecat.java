package com.example.androidapp.managers;

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

    public String getShortDate() {
        return date.substring(0, 10);
    }

    public SimplifiedForecat setDate(String date) {
        this.date = date;
        return this;
    }

    public Double getMinT() {
        return minT;
    }

    public String getMinTempToString() {
        return "min T°" + minT;
    }

    public SimplifiedForecat setMinT(Double minT) {
        this.minT = minT;
        return this;

    }

    public Double getMaxT() {
        return maxT;
    }

    public String getMaxTempToString() {
        return "max T°" + maxT;
    }

    public SimplifiedForecat setWeatherState(String weatherState) {
        this.weatherState = weatherState;
        return this;
    }

    public String getWeatherState() {
        return weatherState.substring(0, 1).toUpperCase() + weatherState.substring(1);
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
        return Objects.equals(date.substring(0, 10), that.date.substring(0, 10));
    }

    @Override
    public int hashCode() {
        return Objects.hash(date.substring(0, 10));
    }

    @Override
    public String toString() {
        return "SimplifiedForecat{" +
                "date='" + date + '\'' +
                ", minT=" + minT +
                ", maxT=" + maxT +
                ", weatherState='" + weatherState + '\'' +
                '}';
    }
}
