package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.shared.BaseRelationDao;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;

import java.util.List;

@Dao
public abstract class ThermometerProfileDao implements BaseRelationDao<ThermometerProfile> {
    private ThermometerProfileMetadataDao thermometerProfileMetadataDao;
    private SensorSettingsDao sensorSettingsDao;

    ThermometerProfileDao(AppDatabase appDatabase) {
        this.thermometerProfileMetadataDao = appDatabase.thermometerProfileMetadataDao();
        this.sensorSettingsDao = appDatabase.sensorSettingsDao();
    }

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata")
    public abstract LiveData<List<ThermometerProfile>> getAllThermometerProfiles();

    @Override
    @Transaction
    public void save(ThermometerProfile entity) {
        int generatedId = (int) thermometerProfileMetadataDao.save(entity.getMetadata());
        for (SensorSettings sensorSettings : entity.getSensorSettings()) {
            sensorSettings.setThermometerProfileMetadataId(generatedId);
            sensorSettingsDao.save(sensorSettings);
        }
    }

    @Override
    @Transaction
    public void update(ThermometerProfile entity) {
        thermometerProfileMetadataDao.update(entity.getMetadata());
        for (SensorSettings sensorSettings : entity.getSensorSettings()) {
            sensorSettingsDao.update(sensorSettings);
        }
    }

    @Override
    @Transaction
    public void delete(ThermometerProfile entity) {
        thermometerProfileMetadataDao.delete(entity.getMetadata());
        for (SensorSettings sensorSettings : entity.getSensorSettings()) {
            sensorSettingsDao.delete(sensorSettings);
        }
    }
}
