package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;


public class ChooseProfileViewModel extends ThermometerProfileViewModel {

    public ChooseProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void onProfileClick(ThermometerProfile thermometerProfile) {
    }
}
