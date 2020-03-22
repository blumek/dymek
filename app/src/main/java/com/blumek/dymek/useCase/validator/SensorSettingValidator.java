package com.blumek.dymek.useCase.validator;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.exception.TemperatureRangeException;

public class SensorSettingValidator {
    public void validate(SensorSetting sensorSetting) {
        if (sensorSetting.getName() == null || sensorSetting.getName().isEmpty())
            throw new IllegalArgumentException("Name not provided");
        if (sensorSetting.getMinTemperatureValue() > sensorSetting.getMaxTemperatureValue())
            throw new TemperatureRangeException("Minimum temperature is higher that maximum temperature");
    }
}
