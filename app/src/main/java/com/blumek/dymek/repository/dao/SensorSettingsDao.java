package com.blumek.dymek.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.model.thermometerProfile.SensorSettings;
import com.blumek.dymek.shared.daos.BaseDao;

import java.util.List;

@Dao
public interface SensorSettingsDao extends BaseDao<SensorSettings> {

    @Query("SELECT * FROM SensorSettings")
    LiveData<List<SensorSettings>> getAllSensorsSettings();

    @Query("SELECT * FROM SensorSettings WHERE thermometerProfileMetadataId=:metadataId")
    LiveData<List<SensorSettings>> getSensorsSettingsByMetadataId(int metadataId);

    @Query("SELECT * FROM SensorSettings WHERE thermometerProfileMetadataId=:metadataId")
    List<SensorSettings> getRawSensorsSettingsByMetadataId(int metadataId);
}