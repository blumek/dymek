package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.repository.ThermometerProfileRepository;
import com.blumek.dymek.repository.ThermometerProfileRepositoryImpl;
import com.blumek.dymek.shared.AppDatabase;

import java.util.List;

public class ThermometerProfileViewModel extends AndroidViewModel {
    ThermometerProfileRepository thermometerProfileRepository;
    private LiveData<List<ThermometerProfile>> thermometersProfiles;

    ThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new ThermometerProfileRepositoryImpl(appDatabase.thermometerProfileDao());
        thermometersProfiles = thermometerProfileRepository.getAllThermometerProfiles();
    }

    public LiveData<List<ThermometerProfile>> getThermometersProfiles() {
        return thermometersProfiles;
    }
}
