package com.blumek.dymek.domain.entity.thermometerProfile;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Value
public class SensorSetting {
    String id;
    String name;
    double minTemperatureValue;
    double maxTemperatureValue;
}
