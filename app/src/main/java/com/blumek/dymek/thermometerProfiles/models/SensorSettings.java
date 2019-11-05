package com.blumek.dymek.thermometerProfiles.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SensorSettings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double minTemperatureValue;
    private double maxTemperatureValue;

    public SensorSettings(String name, double minTemperatureValue, double maxTemperatureValue) {
        this.name = name;
        this.minTemperatureValue = minTemperatureValue;
        this.maxTemperatureValue = maxTemperatureValue;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMinTemperatureValue() {
        return minTemperatureValue;
    }

    public double getMaxTemperatureValue() {
        return maxTemperatureValue;
    }
}
