package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.Objects;

@Entity
public class RoomThermometerProfileMetadata {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private Date lastUsage;
    private Date createdAt;

    public RoomThermometerProfileMetadata(String name, Date createdAt) {
        this.name = name;
        this.createdAt = createdAt;
    }

    private RoomThermometerProfileMetadata() {
    }

    public static RoomThermometerProfileMetadata emptyThermometerProfileMetadata() {
        return new RoomThermometerProfileMetadata();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomThermometerProfileMetadata that = (RoomThermometerProfileMetadata) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastUsage, that.lastUsage) &&
                Objects.equals(createdAt, that.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastUsage, createdAt);
    }
}