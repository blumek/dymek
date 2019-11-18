package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.shared.daos.BaseDao;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;

import java.util.List;

@Dao
public interface ThermometerProfileMetadataDao extends BaseDao<ThermometerProfileMetadata> {

    @Query("SELECT * FROM ThermometerProfileMetadata")
    LiveData<List<ThermometerProfileMetadata>> getAllThermometersProfiles();

    @Query("SELECT * FROM ThermometerProfileMetadata WHERE id=:id")
    LiveData<ThermometerProfileMetadata> getThermometerProfileById(int id);
}
