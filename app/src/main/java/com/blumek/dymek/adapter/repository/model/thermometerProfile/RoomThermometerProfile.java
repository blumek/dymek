package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RoomThermometerProfile implements Serializable {
    @Embedded
    RoomThermometerProfileMetadata metadata;
    @Relation(parentColumn = "id",
            entityColumn = "thermometerProfileMetadataId",
            entity = RoomSensorSettings.class)
    List<RoomSensorSettings> roomSensorSettings;

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
                    .fromWithAssociation(sensorSetting, thermometerProfile.getId()));

        return roomSensorSettings;
    }

    private static RoomThermometerProfileMetadata getThermometerProfileMetadata(ThermometerProfile thermometerProfile) {
        RoomThermometerProfileMetadata roomThermometerProfileMetadata =
                RoomThermometerProfileMetadata.empty();

        roomThermometerProfileMetadata.setId(thermometerProfile.getId());
        roomThermometerProfileMetadata.setName(thermometerProfile.getName());
        roomThermometerProfileMetadata.setCreatedAt(thermometerProfile.getCreatedAt());
        roomThermometerProfileMetadata.setLastUsage(thermometerProfile.getLastUsage());

        return roomThermometerProfileMetadata;
    }
}
