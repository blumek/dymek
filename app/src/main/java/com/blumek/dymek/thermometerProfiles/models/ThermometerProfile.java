package com.blumek.dymek.thermometerProfiles.models;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class ThermometerProfile {
    @Embedded
    private ThermometerProfileMetadata metadata;
    @Relation(parentColumn = "id",
            entityColumn = "thermometerProfileMetadataId",
            entity = SensorSettings.class)
    private List<SensorSettings> sensorSettings;

    public ThermometerProfile() {
    }

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
}
