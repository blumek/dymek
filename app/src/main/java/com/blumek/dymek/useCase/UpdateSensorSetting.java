package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.port.SensorSettingRepository;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;


public final class UpdateSensorSetting {
    private final SensorSettingRepository repository;
    private final SensorSettingValidator validator;

    public UpdateSensorSetting(SensorSettingRepository repository, SensorSettingValidator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    public SensorSetting update(SensorSetting sensorSetting) {
        validator.validate(sensorSetting);

        SensorSetting sensorSettingToUpdate = SensorSetting.builder()
                .id(sensorSetting.getId())
                .name(sensorSetting.getName())
                .minTemperatureValue(sensorSetting.getMinTemperatureValue())
                .maxTemperatureValue(sensorSetting.getMaxTemperatureValue())
                .build();

        repository.update(sensorSetting);
        return sensorSettingToUpdate;
    }
}
