package com.blumek.dymek.repository;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.model.thermometerProfile.ThermometerProfileMetadata;
import com.blumek.dymek.shared.BaseRepository;

import java.util.List;

public interface ThermometerProfileMetadataRepository extends BaseRepository<ThermometerProfileMetadata> {
    LiveData<List<ThermometerProfileMetadata>> getAllThermometerProfiles();
    LiveData<ThermometerProfileMetadata> getThermometerProfileById(int id);
}
