package com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.shared.BaseRepository;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;

import java.util.List;

public interface ThermometerProfileRepository extends BaseRepository<ThermometerProfile> {
    LiveData<List<ThermometerProfile>> getAllThermometerProfiles();
    LiveData<ThermometerProfile> getThermometerProfileByMetadataId(int metadataId);
}
