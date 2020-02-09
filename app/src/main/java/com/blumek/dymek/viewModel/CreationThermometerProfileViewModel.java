package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.model.thermometerProfile.SensorSettings;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfileMetadata;
import com.google.common.collect.Lists;

import java.util.List;

public class CreationThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {

    public CreationThermometerProfileViewModel(@NonNull Application application) {
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
