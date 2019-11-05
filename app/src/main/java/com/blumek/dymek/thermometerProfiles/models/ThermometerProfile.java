package com.blumek.dymek.thermometerProfiles.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ThermometerProfile {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Date lastUsage;
    private Date createdAt;

    public ThermometerProfile(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}