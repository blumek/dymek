package com.blumek.dymek.thermometerProfiles.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SensorSettings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double minTemperatureValue;
    private double maxTemperatureValue;
    private int thermometerProfileMetadataId;

    private SensorSettings() {
    }

    public static SensorSettings emptySensorSettings() {
        return new SensorSettings();
    }

    public SensorSettings(String name, double minTemperatureValue, double maxTemperatureValue) {
        this.name = name;
        this.minTemperatureValue = minTemperatureValue;
        this.maxTemperatureValue = maxTemperatureValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMinTemperatureValue() {
        return minTemperatureValue;
    }

    public void setMinTemperatureValue(double minTemperatureValue) {
        this.minTemperatureValue = minTemperatureValue;
    }

    public double getMaxTemperatureValue() {
        return maxTemperatureValue;
    }

    public void setMaxTemperatureValue(double maxTemperatureValue) {
        this.maxTemperatureValue = maxTemperatureValue;
    }

    public int getThermometerProfileMetadataId() {
        return thermometerProfileMetadataId;
    }

    public void setThermometerProfileMetadataId(int thermometerProfileMetadataId) {
        this.thermometerProfileMetadataId = thermometerProfileMetadataId;
    }

    @NonNull
    @Override
    public String toString() {
        return "SensorSettings{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", minTemperatureValue=" + minTemperatureValue +
                ", maxTemperatureValue=" + maxTemperatureValue +
                ", thermometerProfileMetadataId=" + thermometerProfileMetadataId +
                '}';
    }
}
