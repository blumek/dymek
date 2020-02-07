package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;

import java.util.List;

public class UpdateThermometerProfileFragmentViewModel extends PersistenceThermometerProfileFragmentViewModel {
    public UpdateThermometerProfileFragmentViewModel(@NonNull Application application,
                                              ThermometerProfileMetadata thermometerProfileMetadata,
                                              List<SensorSettings> sensorSettings) {
        super(application);
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
        thermometerProfileRepository.update(thermometerProfile);
    }
}
