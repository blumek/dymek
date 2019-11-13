package com.blumek.dymek.thermometerProfiles.viewModels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.UpdateThermometerProfileFragmentViewModel;

import java.util.List;

public class PersistenceViewModelFactory implements ViewModelProvider.Factory {
    private ThermometerProfileMetadata thermometerProfileMetadata;
    private List<SensorSettings> sensorSettingsList;
    private Application application;

    public PersistenceViewModelFactory(Application application,
                                       ThermometerProfileMetadata thermometerProfileMetadata,
                                       List<SensorSettings> sensorSettingsList) {
        this.application = application;
        this.thermometerProfileMetadata = thermometerProfileMetadata;
        this.sensorSettingsList = sensorSettingsList;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateThermometerProfileFragmentViewModel(application, thermometerProfileMetadata, sensorSettingsList);
    }
}
