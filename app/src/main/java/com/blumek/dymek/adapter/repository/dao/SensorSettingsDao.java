package com.blumek.dymek.adapter.repository.dao;

import androidx.room.Dao;
import androidx.room.Query;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.shared.daos.BaseDao;

import java.util.List;

@Dao
public interface SensorSettingsDao extends BaseDao<RoomSensorSettings> {

    @Query("SELECT * FROM RoomSensorSettings WHERE thermometerProfileMetadataId=:metadataId")
    List<RoomSensorSettings> getRawSensorsSettingsByMetadataId(int metadataId);
}
