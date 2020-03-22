package com.blumek.dymek.useCase.validator;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;

public class ThermometerProfileValidator {
    private final SensorSettingValidator sensorSettingValidator;

    public ThermometerProfileValidator(SensorSettingValidator sensorSettingValidator) {
        this.sensorSettingValidator = sensorSettingValidator;
    }

    public void validate(ThermometerProfile thermometerProfile) {
        if (thermometerProfile.getName() == null || thermometerProfile.getName().isEmpty())
            throw new IllegalArgumentException("Name not provided");

        for (SensorSetting sensorSetting : thermometerProfile.getSensorSettings())
            sensorSettingValidator.validate(sensorSetting);
    }
}
