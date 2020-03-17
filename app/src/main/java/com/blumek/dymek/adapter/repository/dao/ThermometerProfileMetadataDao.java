package com.blumek.dymek.adapter.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.shared.daos.BaseDao;

import java.util.List;

@Dao
public interface ThermometerProfileMetadataDao extends BaseDao<RoomThermometerProfileMetadata> {

    @Query("SELECT * FROM RoomThermometerProfileMetadata")
    LiveData<List<RoomThermometerProfileMetadata>> getAllThermometersProfiles();

    @Query("SELECT * FROM RoomThermometerProfileMetadata WHERE id=:id")
    LiveData<RoomThermometerProfileMetadata> getThermometerProfileById(int id);
}
