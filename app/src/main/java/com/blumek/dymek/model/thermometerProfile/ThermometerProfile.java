package com.blumek.dymek.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class ThermometerProfile implements Serializable {
    @Embedded
    private ThermometerProfileMetadata metadata;
    @Relation(parentColumn = "id",
            entityColumn = "thermometerProfileMetadataId",
            entity = SensorSettings.class)
    private List<SensorSettings> sensorSettings;

    public ThermometerProfile() {
    }

    @Ignore
    public ThermometerProfile(ThermometerProfileMetadata metadata, List<SensorSettings> sensorSettings) {
        this.metadata = metadata;
        this.sensorSettings = sensorSettings;
    }

    public ThermometerProfileMetadata getMetadata() {
        return metadata;
    }

    public List<SensorSettings> getSensorSettings() {
        return sensorSettings;
    }

    public void setMetadata(ThermometerProfileMetadata metadata) {
        this.metadata = metadata;
    }

    public void setSensorSettings(List<SensorSettings> sensorSettings) {
        this.sensorSettings = sensorSettings;
    }

    @NonNull
    @Override
    public String toString() {
        return "ThermometerProfile{" +
                "metadata=" + metadata +
                ", sensorSettings=" + sensorSettings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ThermometerProfile that = (ThermometerProfile) o;
        return Objects.equals(metadata, that.metadata) &&
                Objects.equals(sensorSettings, that.sensorSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, sensorSettings);
    }
}
