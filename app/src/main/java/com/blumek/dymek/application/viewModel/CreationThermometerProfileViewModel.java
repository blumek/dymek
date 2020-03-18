package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.adapter.idGenerator.UUIDGenerator;
import com.blumek.dymek.adapter.repository.AndroidSensorSettingRepository;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.SensorSettingRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.useCase.CreateSensorSetting;
import com.blumek.dymek.useCase.CreateThermometerProfile;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;
import com.google.common.collect.Lists;

import java.util.List;

public class CreationThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final CreateThermometerProfile createThermometerProfile;

    public CreationThermometerProfileViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        SensorSettingRepository sensorSettingRepository =
                new AndroidSensorSettingRepository(appDatabase.sensorSettingsDao());

        createThermometerProfile = new CreateThermometerProfile(thermometerProfileRepository,
                new UUIDGenerator(), new ThermometerProfileValidator(), new CreateSensorSetting(
                sensorSettingRepository,
                new UUIDGenerator(),
                new SensorSettingValidator()
        ));

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
