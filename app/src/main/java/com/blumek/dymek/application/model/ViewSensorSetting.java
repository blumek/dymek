package com.blumek.dymek.application.model;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public final class ViewSensorSetting {
    String id;
    String name;
    double minTemperatureValue;
    double maxTemperatureValue;

    public static ViewSensorSetting empty() {
        return new ViewSensorSetting();
    }

    public SensorSetting toSensorSettings() {
        return SensorSetting.builder()
                .id(this.id)
                .name(this.name)
                .minTemperatureValue(this.minTemperatureValue)
                .maxTemperatureValue(this.maxTemperatureValue)
                .build();
    }

    public SensorSetting toSensorSetting() {
        return SensorSetting.builder()
                .id(this.id)
                .name(this.name)
                .minTemperatureValue(this.minTemperatureValue)
                .maxTemperatureValue(this.maxTemperatureValue)
                .build();
    }

    public static ViewSensorSetting from(SensorSetting sensorSetting) {
        ViewSensorSetting viewSensorSetting = new ViewSensorSetting();
        viewSensorSetting.setId(sensorSetting.getId());
        viewSensorSetting.setName(sensorSetting.getName());
        viewSensorSetting.setMinTemperatureValue(sensorSetting.getMinTemperatureValue());
        viewSensorSetting.setMaxTemperatureValue(sensorSetting.getMaxTemperatureValue());
        return viewSensorSetting;
    }
}
