package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.useCase.FindThermometerProfile;

import java.util.List;

public class ThermometerProfileViewModel extends AndroidViewModel {
    private final FindThermometerProfile findThermometerProfile;
    private final LiveData<List<ThermometerProfile>> thermometersProfiles;
    final ThermometerProfileRepository thermometerProfileRepository;

    ThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());

        findThermometerProfile = new FindThermometerProfile(thermometerProfileRepository);
        thermometersProfiles = findThermometerProfile.findAll();
    }

    public LiveData<List<ThermometerProfile>> getThermometersProfiles() {
        return thermometersProfiles;
    }
}
