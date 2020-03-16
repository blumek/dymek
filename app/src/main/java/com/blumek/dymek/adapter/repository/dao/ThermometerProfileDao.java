package com.blumek.dymek.adapter.repository.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSettings;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfileMetadata;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.shared.daos.BaseRelationDao;

import java.util.List;

@Dao
public abstract class ThermometerProfileDao implements BaseRelationDao<ThermometerProfile> {
    private ThermometerProfileMetadataDao thermometerProfileMetadataDao;
    private SensorSettingsDao sensorSettingsDao;

    public ThermometerProfileDao(AppDatabase appDatabase) {
        this.thermometerProfileMetadataDao = appDatabase.thermometerProfileMetadataDao();
        this.sensorSettingsDao = appDatabase.sensorSettingsDao();
    }

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata")
    public abstract LiveData<List<ThermometerProfile>> getAllThermometerProfiles();

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata WHERE id=:metadataId")
    public abstract LiveData<ThermometerProfile> getThermometerProfileByMetadataId(int metadataId);

    @Override
    @Transaction
    public void save(ThermometerProfile thermometerProfile) {
        int generatedMetadataId = (int) thermometerProfileMetadataDao.save(thermometerProfile.getMetadata());
        associateSensorsSettingsToMetadataByMetadataId(thermometerProfile.getSensorSettings(), generatedMetadataId);
    }

    private void associateSensorsSettingsToMetadataByMetadataId(List<SensorSettings> sensorsSettings,
                                                                int generatedMetadataId) {
        for (SensorSettings sensorSettings : sensorsSettings) {
            associateSensorSettingsToMetadataById(sensorSettings, generatedMetadataId);
        }
    }

    private void associateSensorSettingsToMetadataById(SensorSettings sensorSettings, int metadataId) {
        sensorSettings.setThermometerProfileMetadataId(metadataId);
        sensorSettingsDao.save(sensorSettings);
    }

    @Override
    @Transaction
    public void update(ThermometerProfile thermometerProfile) {
        ThermometerProfileMetadata thermometerProfileMetadata = thermometerProfile.getMetadata();
        thermometerProfileMetadataDao.update(thermometerProfileMetadata);

        List<SensorSettings> sensorsSettings = thermometerProfile.getSensorSettings();
        List<SensorSettings> storedSensorsSettings =
                sensorSettingsDao.getRawSensorsSettingsByMetadataId(thermometerProfileMetadata.getId());

        deleteAbsentSensorsSettings(storedSensorsSettings, sensorsSettings);
        updateAssociatedSensorsSettings(sensorsSettings, thermometerProfileMetadata.getId());
    }

    private void deleteAbsentSensorsSettings(List<SensorSettings> storedSensorsSettings,
                                             List<SensorSettings> sensorsSettings) {
        for (SensorSettings storedSensorSettings : storedSensorsSettings) {
            if (!listContainsId(sensorsSettings, storedSensorSettings.getId()))
                sensorSettingsDao.delete(storedSensorSettings);
        }
    }

    private boolean listContainsId(List<SensorSettings> sensorsSettings, int searchedId) {
        for (SensorSettings sensorSettings : sensorsSettings) {
            if (areIdentifiersEqual(sensorSettings.getId(), searchedId)) {
                return true;
            }
        }
        return false;
    }

    private boolean areIdentifiersEqual(int id, int searchedId) {
        return id == searchedId;
    }

    private void updateAssociatedSensorsSettings(List<SensorSettings> sensorsSettings, int metadataId) {
        for (SensorSettings sensorSettings : sensorsSettings) {
            if (!isAssociated(sensorSettings)) {
                associateSensorSettingsToMetadataById(sensorSettings, metadataId);
            }

            sensorSettingsDao.update(sensorSettings);
        }
    }

    private boolean isAssociated(SensorSettings sensorSettings) {
        return sensorSettings.getId() != 0 &&
                sensorSettings.getThermometerProfileMetadataId() != 0;
    }

    @Override
    @Transaction
    public void delete(ThermometerProfile thermometerProfile) {
        thermometerProfileMetadataDao.delete(thermometerProfile.getMetadata());
        for (SensorSettings sensorSettings : thermometerProfile.getSensorSettings()) {
            sensorSettingsDao.delete(sensorSettings);
        }
    }
}
