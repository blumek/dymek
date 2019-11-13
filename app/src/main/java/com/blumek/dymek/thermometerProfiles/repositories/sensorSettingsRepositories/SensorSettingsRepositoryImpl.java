package com.blumek.dymek.thermometerProfiles.repositories.sensorSettingsRepositories;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.repositories.daos.SensorSettingsDao;

import java.util.List;

public class SensorSettingsRepositoryImpl implements SensorSettingsRepository {
    private SensorSettingsDao sensorSettingsDao;

    public SensorSettingsRepositoryImpl(SensorSettingsDao sensorSettingsDao) {
        this.sensorSettingsDao = sensorSettingsDao;
    }

    @Override
    public LiveData<List<SensorSettings>> getAllSensorsSettings() {
        return sensorSettingsDao.getAllSensorsSettings();
    }

    @Override
    public LiveData<SensorSettings> getSensorsSettingsByMetadataId(int id) {
        return sensorSettingsDao.getSensorsSettingsByMetadataId(id);
    }

    @Override
    public void save(SensorSettings sensorSettings) {
        new AddThermometerProfileAsyncTask(sensorSettingsDao).execute(sensorSettings);
    }

    @Override
    public void update(SensorSettings sensorSettings) {
        new UpdateThermometerProfileAsyncTask(sensorSettingsDao).execute(sensorSettings);
    }

    @Override
    public void delete(SensorSettings sensorSettings) {
        new DeleteThermometerProfileAsyncTask(sensorSettingsDao).execute(sensorSettings);
    }

    private static class AddThermometerProfileAsyncTask extends AsyncTask<SensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private AddThermometerProfileAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(SensorSettings... sensorSettings) {
            sensorSettingsDao.save(sensorSettings[0]);
            return null;
        }
    }

    private static class UpdateThermometerProfileAsyncTask extends AsyncTask<SensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private UpdateThermometerProfileAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(SensorSettings... sensorSettings) {
            sensorSettingsDao.update(sensorSettings[0]);
            return null;
        }
    }

    private static class DeleteThermometerProfileAsyncTask extends AsyncTask<SensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private DeleteThermometerProfileAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(SensorSettings... sensorSettings) {
            sensorSettingsDao.delete(sensorSettings[0]);
            return null;
        }
    }
}
