package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.IdGenerator;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;


public final class CreateThermometerProfile {
    private final ThermometerProfileRepository repository;
    private final IdGenerator idGenerator;
    private final ThermometerProfileValidator validator;
    private final CreateSensorSetting createSensorSetting;

    public CreateThermometerProfile(ThermometerProfileRepository repository,
                                    IdGenerator idGenerator, ThermometerProfileValidator validator,
                                    CreateSensorSetting createSensorSetting) {
        this.repository = repository;
        this.idGenerator = idGenerator;
        this.validator = validator;
        this.createSensorSetting = createSensorSetting;
    }

    public ThermometerProfile create(ThermometerProfile thermometerProfile) {
        validator.validate(thermometerProfile);

        ThermometerProfile thermometerProfileToCreate = ThermometerProfile.builder()
                .id(idGenerator.generate())
                .name(thermometerProfile.getName())
                .createdAt(new Date())
                .sensorSettings(savedSensorSettings(thermometerProfile))
                .build();

        repository.save(thermometerProfileToCreate);
        return thermometerProfileToCreate;
    }

    private List<SensorSetting> savedSensorSettings(ThermometerProfile thermometerProfile) {
        List<SensorSetting> savedSensorSettings = Lists.newArrayList();

        for (SensorSetting sensorSetting : thermometerProfile.getSensorSettings())
            savedSensorSettings.add(createSensorSetting.create(sensorSetting));

        return savedSensorSettings;
    }
}
