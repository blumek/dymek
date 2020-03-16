package com.blumek.dymek.useCase;


import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.repository.ThermometerProfileRepository;


public class UpdateThermometerProfile {
    private ThermometerProfileRepository repository;

    public UpdateThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public void update(ThermometerProfile thermometerProfile) {
        repository.update(thermometerProfile);
    }
}
