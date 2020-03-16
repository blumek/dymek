package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.domain.entity.thermometerProfile.SensorSettings;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfileMetadata;
import com.blumek.dymek.useCase.UpdateThermometerProfile;

import java.util.List;

public class UpdateThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final UpdateThermometerProfile updateThermometerProfile;

    public UpdateThermometerProfileViewModel(@NonNull Application application,
                                             ThermometerProfileMetadata thermometerProfileMetadata,
                                             List<SensorSettings> sensorSettings) {
        super(application);

        updateThermometerProfile = new UpdateThermometerProfile(thermometerProfileRepository);

        setMetadata(thermometerProfileMetadata);
        setSensorsSettingsList(sensorSettings);
    }

    private void setMetadata(ThermometerProfileMetadata thermometerProfileMetadata) {
        metadata = new MutableLiveData<>(thermometerProfileMetadata);
    }

    private void setSensorsSettingsList(List<SensorSettings> sensorsSettings) {
        this.sensorsSettings = new MutableLiveData<>(sensorsSettings);
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        updateThermometerProfile.update(thermometerProfile);
    }
}
