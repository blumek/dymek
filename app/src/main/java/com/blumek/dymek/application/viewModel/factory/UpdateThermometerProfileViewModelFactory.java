package com.blumek.dymek.application.viewModel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.UpdateThermometerProfileViewModel;
import com.blumek.dymek.domain.entity.thermometerProfile.SensorSettings;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfileMetadata;

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
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateThermometerProfileViewModel(application.get(), thermometerProfileMetadata, sensorSettingsList);
    }
}
