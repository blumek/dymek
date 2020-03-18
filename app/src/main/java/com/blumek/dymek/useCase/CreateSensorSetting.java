package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.port.IdGenerator;
import com.blumek.dymek.domain.port.SensorSettingRepository;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;


public final class CreateSensorSetting {
    private final SensorSettingRepository repository;
    private final IdGenerator idGenerator;
    private final SensorSettingValidator validator;

    public CreateSensorSetting(SensorSettingRepository repository,
                               IdGenerator idGenerator,
                               SensorSettingValidator validator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
        this.validator = validator;
    }

    public SensorSetting create(SensorSetting sensorSetting) {
        validator.validate(sensorSetting);

        SensorSetting sensorSettingToCreate = SensorSetting.builder()
                .id(idGenerator.generate())
                .name(sensorSetting.getName())
                .minTemperatureValue(sensorSetting.getMinTemperatureValue())
                .maxTemperatureValue(sensorSetting.getMaxTemperatureValue())
                .build();

        repository.save(sensorSettingToCreate);
        return sensorSettingToCreate;
    }
}
