package com.blumek.dymek.thermometer.models;

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

    void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public Sensor(String name, double temperature) {
        this.name = name;
        this.temperature = temperature;
    }

    public Sensor() {
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "name='" + name + '\'' +
                ", temperature=" + temperature +
                '}';
    }
}
