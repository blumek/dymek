package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.useCase.CreateThermometerProfile;
import com.google.common.collect.Lists;

import java.util.List;

public class CreationThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final CreateThermometerProfile createThermometerProfile;

    public CreationThermometerProfileViewModel(@NonNull Application application) {
        super(application);

        createThermometerProfile = new CreateThermometerProfile(thermometerProfileRepository);

        setEmptyMetadata();
        setEmptySensorsSettingsList();
        addNewEmptySensorSettingsTemplate();
    }

    private void setEmptyMetadata() {
        metadata = new MutableLiveData<>(RoomThermometerProfileMetadata.emptyThermometerProfileMetadata());
    }

    private void setEmptySensorsSettingsList() {
        List<RoomSensorSettings> emptySensorsSettings = Lists.newArrayList();
        sensorsSettings = new MutableLiveData<>(emptySensorsSettings);
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        createThermometerProfile.create(thermometerProfile);
    }
}
