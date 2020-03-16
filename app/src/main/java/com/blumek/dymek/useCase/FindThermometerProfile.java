package com.blumek.dymek.useCase;

import androidx.lifecycle.LiveData;

import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.repository.ThermometerProfileRepository;

import java.util.List;

public class FindThermometerProfile {
    private ThermometerProfileRepository repository;

    public FindThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<ThermometerProfile>> findAll() {
        return repository.getAllThermometerProfiles();
    }
}
