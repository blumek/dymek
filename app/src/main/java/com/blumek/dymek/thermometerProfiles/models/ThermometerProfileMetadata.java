package com.blumek.dymek.thermometerProfiles.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class ThermometerProfileMetadata {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Date lastUsage;
    private Date createdAt;

    public ThermometerProfileMetadata(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    private ThermometerProfileMetadata() {
    }

    public static ThermometerProfileMetadata emptyThermometerProfileMetadata() {
        return new ThermometerProfileMetadata();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUsage() {
        return lastUsage;
    }

    public void setLastUsage(Date lastUsage) {
        this.lastUsage = lastUsage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    @Override
    public String toString() {
        return "ThermometerProfileMetadata{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastUsage=" + lastUsage +
                ", createdAt=" + createdAt +
                '}';
    }
}