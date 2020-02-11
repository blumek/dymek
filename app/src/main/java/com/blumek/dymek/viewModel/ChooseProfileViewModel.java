package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.model.device.Device;


public class ChooseProfileViewModel extends ThermometerProfileViewModel {

    public ChooseProfileViewModel(@NonNull Application application) {
        super(application);
    }

    public void onDeviceClick(Device device) {
        System.out.println(device);
    }
}
