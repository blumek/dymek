package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class RoomThermometerProfile implements Serializable {
    @Embedded
    private RoomThermometerProfileMetadata metadata;
    @Relation(parentColumn = "id",
            entityColumn = "thermometerProfileMetadataId",
            entity = RoomSensorSettings.class)
    private List<RoomSensorSettings> roomSensorSettings;

    public RoomThermometerProfile() {
    }

    @Ignore
    public RoomThermometerProfile(RoomThermometerProfileMetadata metadata, List<RoomSensorSettings> roomSensorSettings) {
        this.metadata = metadata;
        this.roomSensorSettings = roomSensorSettings;
    }

    public RoomThermometerProfileMetadata getMetadata() {
        return metadata;
    }

    public List<RoomSensorSettings> getRoomSensorSettings() {
        return roomSensorSettings;
    }

    public void setMetadata(RoomThermometerProfileMetadata metadata) {
        this.metadata = metadata;
    }

    public void setRoomSensorSettings(List<RoomSensorSettings> roomSensorSettings) {
        this.roomSensorSettings = roomSensorSettings;
    }

    @NonNull
    @Override
    public String toString() {
        return "ThermometerProfile{" +
                "metadata=" + metadata +
                ", sensorSettings=" + roomSensorSettings +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomThermometerProfile that = (RoomThermometerProfile) o;
        return Objects.equals(metadata, that.metadata) &&
                Objects.equals(roomSensorSettings, that.roomSensorSettings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(metadata, roomSensorSettings);
    }

    public ThermometerProfile toThermometerProfile() {
        return ThermometerProfile.builder()
                .id(this.metadata.getId())
                .name(this.metadata.getName())
                .createdAt(this.metadata.getCreatedAt())
                .lastUsage(this.metadata.getLastUsage())
                .sensorSettings(roomSensorSettingsToSensorSettings())
                .build();
    }

    private List<SensorSetting> roomSensorSettingsToSensorSettings() {
        List<SensorSetting> sensorSettings = Lists.newArrayList();
        for (RoomSensorSettings roomSensorSetting : roomSensorSettings)
            sensorSettings.add(roomSensorSetting.toSensorSettings());
        return sensorSettings;
    }

    public static RoomThermometerProfile toRoomThermometerProfile(ThermometerProfile thermometerProfile) {
        Objects.requireNonNull(thermometerProfile);
        RoomThermometerProfile roomThermometerProfile = new RoomThermometerProfile();
        roomThermometerProfile.setMetadata(getThermometerProfileMetadata(thermometerProfile));
        roomThermometerProfile.setRoomSensorSettings(getRoomSensorSettings(thermometerProfile));
        return roomThermometerProfile;
    }

    private static List<RoomSensorSettings> getRoomSensorSettings(ThermometerProfile thermometerProfile) {
        List<RoomSensorSettings> roomSensorSettings = Lists.newArrayList();

        for (SensorSetting sensorSetting : thermometerProfile.getSensorSettings())
            roomSensorSettings.add(RoomSensorSettings
                    .toRoomSensorSettings(sensorSetting, thermometerProfile.getId()));

        return roomSensorSettings;
    }

    private static RoomThermometerProfileMetadata getThermometerProfileMetadata(ThermometerProfile thermometerProfile) {
        RoomThermometerProfileMetadata roomThermometerProfileMetadata =
                RoomThermometerProfileMetadata.emptyThermometerProfileMetadata();

        roomThermometerProfileMetadata.setId(thermometerProfile.getId());
        roomThermometerProfileMetadata.setName(thermometerProfile.getName());
        roomThermometerProfileMetadata.setCreatedAt(thermometerProfile.getCreatedAt());
        roomThermometerProfileMetadata.setLastUsage(thermometerProfile.getLastUsage());

        return roomThermometerProfileMetadata;
    }
}
