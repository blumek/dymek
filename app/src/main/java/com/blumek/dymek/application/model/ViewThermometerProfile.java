package com.blumek.dymek.application.model;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.google.common.collect.Lists;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public final class ViewThermometerProfile implements Serializable {
    String id;
    String name;
    Date lastUsage;
    Date createdAt;
    List<ViewSensorSetting> viewSensorSettings;

    private ViewThermometerProfile() {
        viewSensorSettings = new ArrayList<>();
    }

    public static ViewThermometerProfile empty() {
        return new ViewThermometerProfile();
    }

    public ThermometerProfile toThermometerProfile() {
        return ThermometerProfile.builder()
                .id(this.id)
                .name(this.name)
                .createdAt(this.createdAt)
                .lastUsage(this.lastUsage)
                .sensorSettings(roomSensorSettingsToSensorSettings())
                .build();
    }

    private Collection<? extends SensorSetting> roomSensorSettingsToSensorSettings() {
        List<SensorSetting> sensorSettings = Lists.newArrayList();
        for (ViewSensorSetting sensorSetting : viewSensorSettings)
            sensorSettings.add(sensorSetting.toSensorSettings());
        return sensorSettings;
    }

    public static ViewThermometerProfile from(ThermometerProfile thermometerProfile) {
        ViewThermometerProfile viewThermometerProfile = new ViewThermometerProfile();
        viewThermometerProfile.setId(thermometerProfile.getId());
        viewThermometerProfile.setName(thermometerProfile.getName());
        viewThermometerProfile.setCreatedAt(thermometerProfile.getCreatedAt());
        viewThermometerProfile.setLastUsage(thermometerProfile.getLastUsage());
        viewThermometerProfile.setViewSensorSettings(
                getViewSensorSettings(thermometerProfile.getSensorSettings()));
        return viewThermometerProfile;
    }

    private static List<ViewSensorSetting> getViewSensorSettings(List<SensorSetting> sensorSettings) {
        List<ViewSensorSetting> viewSensorSettings = Lists.newArrayList();
        for (SensorSetting sensorSetting : sensorSettings)
            viewSensorSettings.add(ViewSensorSetting.from(sensorSetting));

        return viewSensorSettings;
    }
}
