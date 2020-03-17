package com.blumek.dymek.domain.entity.thermometerProfile;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Value
public class ThermometerProfile implements Serializable {
    int id;
    String name;
    Date lastUsage;
    Date createdAt;
    @Singular("addSensorSetting")
    List<SensorSetting> sensorSettings;
}
