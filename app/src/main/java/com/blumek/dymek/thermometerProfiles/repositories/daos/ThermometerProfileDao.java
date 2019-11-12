package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;

import java.util.List;

@Dao
public interface ThermometerProfileDao {

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata")
    LiveData<List<ThermometerProfile>> getAllThermometerProfiles();

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata WHERE id=:id")
    LiveData<List<ThermometerProfile>> getThermometerProfilesByMetadataId(int id);
}
