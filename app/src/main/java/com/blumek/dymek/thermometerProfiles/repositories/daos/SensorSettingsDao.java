package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.shared.daos.BaseDao;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;

import java.util.List;

@Dao
public interface SensorSettingsDao extends BaseDao<SensorSettings> {

    @Query("SELECT * FROM SensorSettings")
    LiveData<List<SensorSettings>> getAllSensorsSettings();

    @Query("SELECT * FROM SensorSettings WHERE thermometerProfileMetadataId=:metadataId")
    LiveData<List<SensorSettings>> getSensorsSettingsByMetadataId(int metadataId);
}
