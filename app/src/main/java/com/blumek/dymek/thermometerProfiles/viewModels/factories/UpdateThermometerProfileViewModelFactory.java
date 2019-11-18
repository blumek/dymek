package com.blumek.dymek.thermometerProfiles.viewModels.factories;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels.UpdateThermometerProfileFragmentViewModel;

import java.lang.ref.WeakReference;
import java.util.List;

public class UpdateThermometerProfileViewModelFactory implements ViewModelProvider.Factory {
    private ThermometerProfileMetadata thermometerProfileMetadata;
    private List<SensorSettings> sensorSettingsList;
    private WeakReference<Application> application;

    public UpdateThermometerProfileViewModelFactory(Application application, ThermometerProfile thermometerProfile) {
        this.application = new WeakReference<>(application);
        this.thermometerProfileMetadata = thermometerProfile.getMetadata();
        this.sensorSettingsList = thermometerProfile.getSensorSettings();
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateThermometerProfileFragmentViewModel(application.get(), thermometerProfileMetadata, sensorSettingsList);
    }
}
