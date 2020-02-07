package com.blumek.dymek.model.sensor;

import androidx.annotation.NonNull;

public class Sensor {
    private String name;
    private double temperature;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Sensor(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public static Sensor empty() {
        return new Sensor();
    }

    private Sensor() {
    }

    @NonNull
    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
