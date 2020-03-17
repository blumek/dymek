package com.blumek.dymek.application.viewModel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.application.viewModel.UpdateThermometerProfileViewModel;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;

public class UpdateThermometerProfileViewModelFactory implements ViewModelProvider.Factory {
    private final ThermometerProfile thermometerProfile;
    private final Application application;

    public UpdateThermometerProfileViewModelFactory(Application application, ThermometerProfile thermometerProfile) {
        this.application = application;
        this.thermometerProfile = thermometerProfile;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateThermometerProfileViewModel(application, thermometerProfile);
    }
}
