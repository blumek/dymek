package com.blumek.dymek.repository;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.shared.BaseRepository;

import java.util.List;

public interface ThermometerProfileRepository extends BaseRepository<ThermometerProfile> {
    LiveData<List<ThermometerProfile>> getAllThermometerProfiles();
    LiveData<ThermometerProfile> getThermometerProfileByMetadataId(int metadataId);
}
