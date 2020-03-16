package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;


public class UpdateThermometerProfile {
    private ThermometerProfileRepository repository;

    public UpdateThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public void update(ThermometerProfile thermometerProfile) {
        repository.update(thermometerProfile);
    }
}
