package com.example.androidapp.managers;

public class ComplexForecast extends SimplifiedForecast {

    Double windSpeed;
    Double windDegrees;
    String humidity;
    Double pressure;
    String ShortData;

    public ComplexForecast() {
    }

    public ComplexForecast(String date, String weatherState, Double minT, Double maxT, Double windSpeed, Double windDegrees, String humidity, Double pressure) {
        this.date = date;
        this.weatherState = weatherState;
        this.minT = minT;
        this.maxT = maxT;
        this.windSpeed = windSpeed;
        this.windDegrees = windDegrees;
        this.humidity = humidity;
        this.pressure = pressure;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getWindSpeedToString() {
        return String.format("Wind speed: %s m/s", windSpeed);
    }

    public ComplexForecast setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public Double getWindDegrees() {
        return windDegrees;
    }

    public String getWindDegreesToString() {
        return String.format("Wind direction: %s°", windDegrees);
    }

    public ComplexForecast setWindDegrees(Double windDegrees) {
        this.windDegrees = windDegrees;
        return this;
    }

    public String getHumidity() {
        return String.format("Humidity: %s%s", humidity, "%");
    }

    public ComplexForecast setHumidity(String humidity) {
        this.humidity = humidity;
        return this;
    }

    public Double getPressure() {
        return pressure;
    }

    public String getPressureToString() {
        return String.format("Pressure: %shPa", pressure);
    }

    public ComplexForecast setPressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    @Override
    public String toString() {
        return "ComplexForecast{" +
                "date='" + date + '\'' +
                ", weatherState='" + weatherState + '\'' +
                ", minT=" + minT +
                ", maxT=" + maxT +
                ", windSpeed=" + windSpeed +
                ", windDegrees=" + windDegrees +
                ", humidity=" + humidity +
                ", pressure=" + pressure +
                '}';
    }
}
