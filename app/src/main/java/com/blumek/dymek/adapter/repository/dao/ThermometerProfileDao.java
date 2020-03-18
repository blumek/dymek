package com.blumek.dymek.adapter.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfile;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.shared.daos.BaseRelationDao;

import java.util.List;

@Dao
public abstract class ThermometerProfileDao implements BaseRelationDao<RoomThermometerProfile> {
    private ThermometerProfileMetadataDao thermometerProfileMetadataDao;
    private SensorSettingsDao sensorSettingsDao;

    ThermometerProfileDao(AppDatabase appDatabase) {
        this.thermometerProfileMetadataDao = appDatabase.thermometerProfileMetadataDao();
        this.sensorSettingsDao = appDatabase.sensorSettingsDao();
    }

    @Transaction
    @Query("SELECT * FROM RoomThermometerProfileMetadata")
    public abstract LiveData<List<RoomThermometerProfile>> getAllThermometerProfiles();

    @Transaction
    @Query("SELECT * FROM RoomThermometerProfileMetadata WHERE id=:metadataId")
    public abstract LiveData<RoomThermometerProfile> getThermometerProfileByMetadataId(String metadataId);

    @Override
    @Transaction
    public void save(RoomThermometerProfile roomThermometerProfile) {
        saveThermometerProfileMetadata(roomThermometerProfile.getMetadata());
        associateSensorSettingsToThermometerProfile(roomThermometerProfile.getMetadata().getId(),
                roomThermometerProfile.getRoomSensorSettings());
    }

    private void saveThermometerProfileMetadata(RoomThermometerProfileMetadata metadata) {
        thermometerProfileMetadataDao.save(metadata);
    }

    private void associateSensorSettingsToThermometerProfile(String id, List<RoomSensorSettings> roomSensorSettings) {
        for (RoomSensorSettings sensorSettings : roomSensorSettings) {
            sensorSettings.setThermometerProfileMetadataId(id);
            sensorSettingsDao.update(sensorSettings);
        }
    }

    @Override
    @Transaction
    public void update(RoomThermometerProfile roomThermometerProfile) {
        RoomThermometerProfileMetadata roomThermometerProfileMetadata = roomThermometerProfile.getMetadata();
        thermometerProfileMetadataDao.update(roomThermometerProfileMetadata);

        associateSensorSettingsToThermometerProfile(roomThermometerProfile.getMetadata().getId(),
                roomThermometerProfile.getRoomSensorSettings());

        List<RoomSensorSettings> sensorsSettings = roomThermometerProfile.getRoomSensorSettings();
        List<RoomSensorSettings> storedSensorsSettings =
                sensorSettingsDao.getRawSensorsSettingsByMetadataId(roomThermometerProfileMetadata.getId());

        deleteAbsentSensorsSettings(storedSensorsSettings, sensorsSettings);
    }

    private void deleteAbsentSensorsSettings(List<RoomSensorSettings> storedSensorsSettings,
                                             List<RoomSensorSettings> sensorsSettings) {
        for (RoomSensorSettings storedRoomSensorSettings : storedSensorsSettings) {
            if (!listContainsId(sensorsSettings, storedRoomSensorSettings.getId()))
                sensorSettingsDao.delete(storedRoomSensorSettings);
        }
    }

    private boolean listContainsId(List<RoomSensorSettings> sensorsSettings, String searchedId) {
        for (RoomSensorSettings roomSensorSettings : sensorsSettings) {
            if (areIdentifiersEqual(roomSensorSettings.getId(), searchedId)) {
                return true;
            }
        }
        return false;
    }

    private boolean areIdentifiersEqual(String id, String searchedId) {
        return id != null && id.equals(searchedId);
    }

    @Override
    @Transaction
    public void delete(RoomThermometerProfile roomThermometerProfile) {
        thermometerProfileMetadataDao.delete(roomThermometerProfile.getMetadata());
        for (RoomSensorSettings roomSensorSettings : roomThermometerProfile.getRoomSensorSettings()) {
            sensorSettingsDao.delete(roomSensorSettings);
        }
    }
}
