package com.blumek.dymek.thermometerProfiles.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blumek.dymek.thermometerProfiles.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileRepository;

import java.util.List;

public class ThermometerProfileViewModel extends AndroidViewModel {
    private ThermometerProfileRepository repository;
    private LiveData<List<ThermometerProfile>> thermometerProfiles;

    public ThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        repository = new ThermometerProfileRepository(application);
        thermometerProfiles = repository.getThermometerProfiles();
    }

    public void add(ThermometerProfile thermometerProfile) {
        repository.add(thermometerProfile);
    }

    public void update(ThermometerProfile thermometerProfile) {
        repository.update(thermometerProfile);
    }

    public void delete(ThermometerProfile thermometerProfile) {
        repository.delete(thermometerProfile);
    }
    public LiveData<List<ThermometerProfile>> getThermometerProfiles() {
        return thermometerProfiles;
    }
}
