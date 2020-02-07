package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.google.common.collect.Lists;

import java.util.List;

public class CreationThermometerProfileFragmentViewModel extends PersistenceThermometerProfileFragmentViewModel {

    public CreationThermometerProfileFragmentViewModel(@NonNull Application application) {
        super(application);

        setEmptyMetadata();
        setEmptySensorsSettingsList();
        addNewEmptySensorSettingsTemplate();
    }

    private void setEmptyMetadata() {
        metadata = new MutableLiveData<>(ThermometerProfileMetadata.emptyThermometerProfileMetadata());
    }

    private void setEmptySensorsSettingsList() {
        List<SensorSettings> emptySensorsSettings = Lists.newArrayList();
        sensorsSettings = new MutableLiveData<>(emptySensorsSettings);
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        thermometerProfileRepository.save(thermometerProfile);
    }
}
