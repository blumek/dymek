package com.blumek.dymek.thermometerProfiles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class SensorSettings {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private double minTemperatureValue;
    private double maxTemperatureValue;
}
