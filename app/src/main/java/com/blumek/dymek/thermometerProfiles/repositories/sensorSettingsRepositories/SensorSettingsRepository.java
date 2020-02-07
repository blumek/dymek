package com.blumek.dymek.thermometerProfiles.repositories.sensorSettingsRepositories;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.shared.BaseRepository;
import com.blumek.dymek.model.thermometerProfile.SensorSettings;

import java.util.List;

public interface SensorSettingsRepository extends BaseRepository<SensorSettings> {
    LiveData<List<SensorSettings>> getAllSensorsSettings();
    LiveData<List<SensorSettings>> getSensorsSettingsByMetadataId(int metadataId);
}
