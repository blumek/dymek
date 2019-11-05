package com.blumek.dymek.thermometerProfiles.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerRepositoryImpl;

import java.util.List;

public class ThermometerProfileViewModel extends AndroidViewModel {
    private ThermometerProfileRepository thermometerProfileRepository;
    private LiveData<List<ThermometerProfile>> thermometerProfiles;

    public ThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository = new ThermometerRepositoryImpl(appDatabase.thermometerProfileDao());
        thermometerProfiles = thermometerProfileRepository.getAllThermometerProfiles();
    }

    public LiveData<List<ThermometerProfile>> getThermometerProfiles() {
        return thermometerProfiles;
    }
}
