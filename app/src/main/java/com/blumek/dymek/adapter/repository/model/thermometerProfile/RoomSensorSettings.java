package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;

import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import static androidx.room.ForeignKey.CASCADE;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity(foreignKeys ={
        @ForeignKey(onDelete = CASCADE, onUpdate = CASCADE,
                entity = RoomThermometerProfileMetadata.class,
                parentColumns = "id", childColumns = "thermometerProfileMetadataId")}
        )
public class RoomSensorSettings {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private double minTemperatureValue;
    private double maxTemperatureValue;
    private String thermometerProfileMetadataId;

    public SensorSetting toSensorSettings() {
        return SensorSetting.builder()
                .id(this.id)
                .name(this.name)
                .minTemperatureValue(this.minTemperatureValue)
                .maxTemperatureValue(this.maxTemperatureValue)
                .build();
    }

    public static RoomSensorSettings from(final SensorSetting sensorSetting) {
        Objects.requireNonNull(sensorSetting);
        RoomSensorSettings roomSensorSettings = new RoomSensorSettings();
        roomSensorSettings.setId(sensorSetting.getId());
        roomSensorSettings.setName(sensorSetting.getName());
        roomSensorSettings.setMinTemperatureValue(sensorSetting.getMinTemperatureValue());
        roomSensorSettings.setMaxTemperatureValue(sensorSetting.getMaxTemperatureValue());
        return roomSensorSettings;
    }

    public static RoomSensorSettings fromWithAssociation(final SensorSetting sensorSetting,
                                                         String thermometerProfileMetadataId) {
        RoomSensorSettings roomSensorSettings = from(sensorSetting);
        roomSensorSettings.setThermometerProfileMetadataId(thermometerProfileMetadataId);
        return roomSensorSettings;
    }
}
