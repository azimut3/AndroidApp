package com.example.androidapp.managers;

public class ComplexForecast {
    String date;
    String weatherState;
    Double minT;
    Double maxT;
    Double windSpeed;
    Double windDegrees;
    String humidity;
    Double pressure;

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

    public String getDate() {
        return date;
    }

    public String getShortDate() {
        return date.substring(0, 10);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWeatherState() {
        return weatherState.substring(0, 1).toUpperCase() + weatherState.substring(1);
    }

    public void setWeatherState(String weatherState) {
        this.weatherState = weatherState;
    }

    public Double getMinT() {
        return minT;
    }

    public String getMinTempToString() {
        return "min T°" + minT;
    }

    public void setMinT(Double minT) {
        this.minT = minT;
    }

    public Double getMaxT() {
        return maxT;
    }

    public String getMaxTempToString() {
        return "max T°" + maxT;
    }

    public void setMaxT(Double maxT) {
        this.maxT = maxT;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public String getWindSpeedToString() {
        return String.format("Wind speed: %s m/s", windSpeed);
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDegrees() {
        return windDegrees;
    }

    public String getWindDegreesToString() {
        return String.format("Wind direction: %s°", windDegrees);
    }

    public void setWindDegrees(Double windDegrees) {
        this.windDegrees = windDegrees;
    }

    public String getHumidity() {
        return String.format("Humidity: %s%s", humidity, "%");
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public Double getPressure() {
        return pressure;
    }

    public String getPressureToString() {
        return String.format("Pressure: %shPa", pressure);
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
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
