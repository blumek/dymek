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
    public abstract LiveData<RoomThermometerProfile> getThermometerProfileByMetadataId(int metadataId);

    @Override
    @Transaction
    public void save(RoomThermometerProfile roomThermometerProfile) {
        int generatedMetadataId = (int) thermometerProfileMetadataDao.save(roomThermometerProfile.getMetadata());
        associateSensorsSettingsToMetadataByMetadataId(roomThermometerProfile.getRoomSensorSettings(), generatedMetadataId);
    }

    private void associateSensorsSettingsToMetadataByMetadataId(List<RoomSensorSettings> sensorsSettings,
                                                                int generatedMetadataId) {
        for (RoomSensorSettings roomSensorSettings : sensorsSettings) {
            associateSensorSettingsToMetadataById(roomSensorSettings, generatedMetadataId);
        }
    }

    private void associateSensorSettingsToMetadataById(RoomSensorSettings roomSensorSettings, int metadataId) {
        roomSensorSettings.setThermometerProfileMetadataId(metadataId);
        sensorSettingsDao.save(roomSensorSettings);
    }

    @Override
    @Transaction
    public void update(RoomThermometerProfile roomThermometerProfile) {
        RoomThermometerProfileMetadata roomThermometerProfileMetadata = roomThermometerProfile.getMetadata();
        thermometerProfileMetadataDao.update(roomThermometerProfileMetadata);

        List<RoomSensorSettings> sensorsSettings = roomThermometerProfile.getRoomSensorSettings();
        List<RoomSensorSettings> storedSensorsSettings =
                sensorSettingsDao.getRawSensorsSettingsByMetadataId(roomThermometerProfileMetadata.getId());

        deleteAbsentSensorsSettings(storedSensorsSettings, sensorsSettings);
        updateAssociatedSensorsSettings(sensorsSettings, roomThermometerProfileMetadata.getId());
    }

    private void deleteAbsentSensorsSettings(List<RoomSensorSettings> storedSensorsSettings,
                                             List<RoomSensorSettings> sensorsSettings) {
        for (RoomSensorSettings storedRoomSensorSettings : storedSensorsSettings) {
            if (!listContainsId(sensorsSettings, storedRoomSensorSettings.getId()))
                sensorSettingsDao.delete(storedRoomSensorSettings);
        }
    }

    private boolean listContainsId(List<RoomSensorSettings> sensorsSettings, int searchedId) {
        for (RoomSensorSettings roomSensorSettings : sensorsSettings) {
            if (areIdentifiersEqual(roomSensorSettings.getId(), searchedId)) {
                return true;
            }
        }
        return false;
    }

    private boolean areIdentifiersEqual(int id, int searchedId) {
        return id == searchedId;
    }

    private void updateAssociatedSensorsSettings(List<RoomSensorSettings> sensorsSettings, int metadataId) {
        for (RoomSensorSettings roomSensorSettings : sensorsSettings) {
            if (!isAssociated(roomSensorSettings)) {
                associateSensorSettingsToMetadataById(roomSensorSettings, metadataId);
            }

            sensorSettingsDao.update(roomSensorSettings);
        }
    }

    private boolean isAssociated(RoomSensorSettings roomSensorSettings) {
        return roomSensorSettings.getId() != 0 &&
                roomSensorSettings.getThermometerProfileMetadataId() != 0;
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
