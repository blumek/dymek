package com.blumek.dymek.thermometerProfiles.repositories.sensorSettingsRepositories;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.shared.BaseRepository;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;

import java.util.List;

public interface SensorSettingsRepository extends BaseRepository<SensorSettings> {
    LiveData<List<SensorSettings>> getAllSensorsSettings();
    LiveData<SensorSettings> getSensorsSettingsByMetadataId(int metadataId);
}
