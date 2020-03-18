package com.blumek.dymek.adapter.repository;

import android.os.AsyncTask;

import com.blumek.dymek.adapter.repository.dao.SensorSettingsDao;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.domain.entity.thermometerProfile.SensorSetting;
import com.blumek.dymek.domain.port.SensorSettingRepository;

public class AndroidSensorSettingRepository implements SensorSettingRepository {
    private SensorSettingsDao sensorSettingsDao;

    public AndroidSensorSettingRepository(SensorSettingsDao sensorSettingsDao) {
        this.sensorSettingsDao = sensorSettingsDao;
    }

    @Override
    public void save(SensorSetting sensorSetting) {
        new AddSensorSettingAsyncTask(sensorSettingsDao)
                .execute(RoomSensorSettings.toRoomSensorSettings(sensorSetting));
    }

    @Override
    public void update(SensorSetting sensorSetting) {
        new UpdateSensorSettingAsyncTask(sensorSettingsDao)
                .execute(RoomSensorSettings.toRoomSensorSettings(sensorSetting));
    }

    @Override
    public void delete(SensorSetting sensorSetting) {
        new DeleteSensorSettingAsyncTask(sensorSettingsDao)
                .execute(RoomSensorSettings.toRoomSensorSettings(sensorSetting));
    }

    private static class AddSensorSettingAsyncTask extends AsyncTask<RoomSensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private AddSensorSettingAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(RoomSensorSettings... roomSensorSettings) {
            sensorSettingsDao.save(roomSensorSettings[0]);
            return null;
        }
    }

    private static class UpdateSensorSettingAsyncTask extends AsyncTask<RoomSensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private UpdateSensorSettingAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(RoomSensorSettings... roomSensorSettings) {
            sensorSettingsDao.update(roomSensorSettings[0]);
            return null;
        }
    }

    private static class DeleteSensorSettingAsyncTask extends AsyncTask<RoomSensorSettings, Void, Void> {
        private SensorSettingsDao sensorSettingsDao;

        private DeleteSensorSettingAsyncTask(SensorSettingsDao sensorSettingsDao) {
            this.sensorSettingsDao = sensorSettingsDao;
        }

        @Override
        protected Void doInBackground(RoomSensorSettings... roomSensorSettings) {
            sensorSettingsDao.delete(roomSensorSettings[0]);
            return null;
        }
    }
}
