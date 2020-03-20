package com.blumek.dymek.domain.entity.thermometerProfile;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Value
public class SensorSetting implements Serializable {
    String id;
    String name;
    double minTemperatureValue;
    double maxTemperatureValue;
}
