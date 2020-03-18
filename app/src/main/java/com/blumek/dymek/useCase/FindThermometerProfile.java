package com.blumek.dymek.useCase;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;

import java.util.List;

public final class FindThermometerProfile {
    private final ThermometerProfileRepository repository;

    public FindThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ThermometerProfile>> findAll() {
        return repository.getAllThermometerProfiles();
    }
}
