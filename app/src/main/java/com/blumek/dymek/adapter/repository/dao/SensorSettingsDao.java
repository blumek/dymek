package com.blumek.dymek.adapter.repository.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSettings;
import com.blumek.dymek.shared.daos.BaseDao;

import java.util.List;

@Dao
public interface SensorSettingsDao extends BaseDao<SensorSettings> {

    @Query("SELECT * FROM SensorSettings WHERE thermometerProfileMetadataId=:metadataId")
    List<SensorSettings> getRawSensorsSettingsByMetadataId(int metadataId);
}
