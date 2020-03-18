package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;
import com.google.common.collect.Lists;

import java.util.List;


public final class UpdateThermometerProfile {
    private final ThermometerProfileRepository repository;
    private final ThermometerProfileValidator validator;
    private final CreateSensorSetting createSensorSetting;
    private final UpdateSensorSetting updateSensorSetting;

    public UpdateThermometerProfile(ThermometerProfileRepository repository,
                                    ThermometerProfileValidator validator,
                                    CreateSensorSetting createSensorSetting,
                                    UpdateSensorSetting updateSensorSetting) {
        this.repository = repository;
        this.validator = validator;
        this.createSensorSetting = createSensorSetting;
        this.updateSensorSetting = updateSensorSetting;
    }

    public void update(ThermometerProfile thermometerProfile) {
        validator.validate(thermometerProfile);

        ThermometerProfile thermometerProfileToUpdate = ThermometerProfile.builder()
                .id(thermometerProfile.getId())
                .name(thermometerProfile.getName())
                .createdAt(thermometerProfile.getCreatedAt())
                .lastUsage(thermometerProfile.getLastUsage())
                .sensorSettings(updateSensorSettings(thermometerProfile.getSensorSettings()))
                .build();

        repository.update(thermometerProfileToUpdate);
    }

    private List<SensorSetting> updateSensorSettings(List<SensorSetting> sensorSettings) {
        List<SensorSetting> sensorSettingsToPersist = Lists.newArrayList();

        for (SensorSetting sensorSetting : sensorSettings) {
            if (!isAlreadySaved(sensorSetting))
                sensorSettingsToPersist.add(createSensorSetting.create(sensorSetting));
            else
                sensorSettingsToPersist.add(updateSensorSetting.update(sensorSetting));
        }

        return sensorSettingsToPersist;
    }

    private boolean isAlreadySaved(SensorSetting sensorSetting) {
        return hasId(sensorSetting);
    }

    private boolean hasId(SensorSetting sensorSetting) {
        return sensorSetting.getId() != null && !sensorSetting.getId().isEmpty();
    }
}
