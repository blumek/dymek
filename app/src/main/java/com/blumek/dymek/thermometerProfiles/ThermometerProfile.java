package com.blumek.dymek.thermometerProfiles;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "thermometer_profiles")
public class ThermometerProfile {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;

    public ThermometerProfile(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
