package com.blumek.dymek.domain.port;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.shared.BaseRepository;

import java.util.List;

public interface ThermometerProfileRepository extends BaseRepository<ThermometerProfile> {
    LiveData<List<ThermometerProfile>> getAllThermometerProfiles();
}
