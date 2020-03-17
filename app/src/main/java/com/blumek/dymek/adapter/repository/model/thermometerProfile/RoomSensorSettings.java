package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;

import java.util.Objects;

import static androidx.room.ForeignKey.CASCADE;

@Entity(foreignKeys ={
        @ForeignKey(onDelete = CASCADE, onUpdate = CASCADE,
                entity = RoomThermometerProfileMetadata.class,
                parentColumns = "id",childColumns = "thermometerProfileMetadataId")}
        )
public class RoomSensorSettings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double minTemperatureValue;
    private double maxTemperatureValue;
    private int thermometerProfileMetadataId;

    private RoomSensorSettings() {
    }

    public static RoomSensorSettings emptySensorSettings() {
        return new RoomSensorSettings();
    }

    public RoomSensorSettings(String name, double minTemperatureValue, double maxTemperatureValue) {
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
        RoomSensorSettings that = (RoomSensorSettings) o;
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

    public SensorSetting toSensorSettings() {
        return SensorSetting.builder()
                .id(this.id)
                .name(this.name)
                .minTemperatureValue(this.minTemperatureValue)
                .maxTemperatureValue(this.maxTemperatureValue)
                .build();
    }

    public static RoomSensorSettings toRoomSensorSettings(final SensorSetting sensorSetting) {
        Objects.requireNonNull(sensorSetting);
        RoomSensorSettings roomSensorSettings = new RoomSensorSettings();
        roomSensorSettings.setId(sensorSetting.getId());
        roomSensorSettings.setName(sensorSetting.getName());
        roomSensorSettings.setMinTemperatureValue(sensorSetting.getMinTemperatureValue());
        roomSensorSettings.setMaxTemperatureValue(sensorSetting.getMaxTemperatureValue());
        return roomSensorSettings;
    }

    public static RoomSensorSettings toRoomSensorSettings(final SensorSetting sensorSetting,
                                                          int thermometerProfileMetadataId) {
        RoomSensorSettings roomSensorSettings = toRoomSensorSettings(sensorSetting);
        roomSensorSettings.setThermometerProfileMetadataId(thermometerProfileMetadataId);
        return roomSensorSettings;
    }
}
