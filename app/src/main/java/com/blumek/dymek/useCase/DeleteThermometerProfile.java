package com.blumek.dymek.useCase;


import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;


public class DeleteThermometerProfile {
    private ThermometerProfileRepository repository;

    public DeleteThermometerProfile(ThermometerProfileRepository repository) {
        this.repository = repository;
    }

    public void delete(ThermometerProfile thermometerProfile) {
        repository.delete(thermometerProfile);
    }
}
