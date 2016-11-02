package com.itis.androidlab.service.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FullWeatherInfo {

    @JsonProperty("coord")
    private Coordinates coordinates;

    private List<Weather> weather;

    private String base;

    @JsonProperty("main")
    private Temperature temperature;

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "FullWeatherInfo{" +
                "coordinates=" + coordinates +
                ", weather=" + weather +
                ", base='" + base + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
