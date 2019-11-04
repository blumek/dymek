package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.blumek.dymek.thermometerProfiles.ThermometerProfile;

import java.util.List;

@Dao
public interface ThermometerProfileDao {

    @Insert
    void add(ThermometerProfile thermometerProfile);

    @Update
    void update(ThermometerProfile thermometerProfile);

    @Delete
    void delete(ThermometerProfile thermometerProfile);

    @Query("SELECT * FROM thermometer_profiles")
    LiveData<List<ThermometerProfile>> getThermometerProfiles();
}
