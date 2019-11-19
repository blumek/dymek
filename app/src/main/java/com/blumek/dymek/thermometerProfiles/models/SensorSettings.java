package com.blumek.dymek.thermometerProfiles.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys ={
        @ForeignKey(onDelete = CASCADE, onUpdate = CASCADE,
                entity = ThermometerProfileMetadata.class,
                parentColumns = "id",childColumns = "thermometerProfileMetadataId")},
        indices = {
                @Index("thermometerProfileMetadataId"),
        })
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SensorSettings that = (SensorSettings) o;
        return id == that.id &&
                Double.compare(that.minTemperatureValue, minTemperatureValue) == 0 &&
                Double.compare(that.maxTemperatureValue, maxTemperatureValue) == 0 &&
                thermometerProfileMetadataId == that.thermometerProfileMetadataId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, minTemperatureValue, maxTemperatureValue, thermometerProfileMetadataId);
    }
}
