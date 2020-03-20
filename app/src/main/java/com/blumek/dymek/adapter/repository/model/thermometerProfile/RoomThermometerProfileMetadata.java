package com.blumek.dymek.adapter.repository.model.thermometerProfile;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
public class RoomThermometerProfileMetadata {
    @PrimaryKey
    @NonNull
    private String id;
    private String name;
    private Date lastUsage;
    private Date createdAt;

    public static RoomThermometerProfileMetadata empty() {
        return new RoomThermometerProfileMetadata();
    }
}