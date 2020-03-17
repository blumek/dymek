package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfile;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.useCase.UpdateThermometerProfile;

import java.util.List;

public class UpdateThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final UpdateThermometerProfile updateThermometerProfile;

    public UpdateThermometerProfileViewModel(@NonNull Application application,
                                             ThermometerProfile thermometerProfile) {
        super(application);

        updateThermometerProfile = new UpdateThermometerProfile(thermometerProfileRepository);

        RoomThermometerProfile roomThermometerProfile = RoomThermometerProfile
                .toRoomThermometerProfile(thermometerProfile);
        setMetadata(roomThermometerProfile.getMetadata());
        setSensorsSettingsList(roomThermometerProfile.getRoomSensorSettings());
    }

    private void setMetadata(RoomThermometerProfileMetadata roomThermometerProfileMetadata) {
        metadata = new MutableLiveData<>(roomThermometerProfileMetadata);
    }

    private void setSensorsSettingsList(List<RoomSensorSettings> sensorsSettings) {
        this.sensorsSettings = new MutableLiveData<>(sensorsSettings);
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        updateThermometerProfile.update(thermometerProfile);
    }
}
