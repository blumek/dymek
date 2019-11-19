package com.blumek.dymek.thermometerProfiles.repositories.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;
import androidx.room.Transaction;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.shared.daos.BaseRelationDao;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;

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

    @Transaction
    @Query("SELECT * FROM ThermometerProfileMetadata WHERE id=:metadataId")
    public abstract LiveData<ThermometerProfile> getThermometerProfileByMetadataId(int metadataId);

    @Override
    @Transaction
    public void save(ThermometerProfile thermometerProfile) {
        int generatedId = (int) thermometerProfileMetadataDao.save(thermometerProfile.getMetadata());
        for (SensorSettings sensorSettings : thermometerProfile.getSensorSettings()) {
            sensorSettings.setThermometerProfileMetadataId(generatedId);
            sensorSettingsDao.save(sensorSettings);
        }
    }

    @Override
    @Transaction
    public void update(ThermometerProfile thermometerProfile) {
        ThermometerProfileMetadata thermometerProfileMetadata = thermometerProfile.getMetadata();
        thermometerProfileMetadataDao.update(thermometerProfileMetadata);
        for (SensorSettings sensorSettings : thermometerProfile.getSensorSettings()) {
            if (!isAssociated(sensorSettings)) {
                sensorSettings.setThermometerProfileMetadataId(thermometerProfileMetadata.getId());
                sensorSettingsDao.save(sensorSettings);
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
