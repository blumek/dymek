package com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileMetadataRepositories;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.shared.BaseRepository;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfileMetadata;

import java.util.List;

public interface ThermometerProfileMetadataRepository extends BaseRepository<ThermometerProfileMetadata> {
    LiveData<List<ThermometerProfileMetadata>> getAllThermometerProfiles();
    LiveData<ThermometerProfileMetadata> getThermometerProfileById(int id);
}
