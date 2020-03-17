package com.blumek.dymek.application.viewModel.factory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfile;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.application.viewModel.UpdateThermometerProfileViewModel;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;

import java.lang.ref.WeakReference;
import java.util.List;

public class UpdateThermometerProfileViewModelFactory implements ViewModelProvider.Factory {
    private RoomThermometerProfileMetadata roomThermometerProfileMetadata;
    private List<RoomSensorSettings> roomSensorSettingsList;
    private WeakReference<Application> application;

    public UpdateThermometerProfileViewModelFactory(Application application, ThermometerProfile thermometerProfile) {
        this.application = new WeakReference<>(application);
        this.roomThermometerProfileMetadata = RoomThermometerProfile.toRoomThermometerProfile(thermometerProfile).getMetadata();
        this.roomSensorSettingsList = RoomThermometerProfile.toRoomThermometerProfile(thermometerProfile).getRoomSensorSettings();
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UpdateThermometerProfileViewModel(application.get(), roomThermometerProfileMetadata, roomSensorSettingsList);
    }
}
