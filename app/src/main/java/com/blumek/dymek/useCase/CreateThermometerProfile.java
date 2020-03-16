package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;


public class CreateThermometerProfile {
    private ThermometerProfileRepository repository;

    public CreateThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public void create(ThermometerProfile thermometerProfile) {
        repository.save(thermometerProfile);
    }
}
