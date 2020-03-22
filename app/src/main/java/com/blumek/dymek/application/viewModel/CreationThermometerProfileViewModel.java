package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.adapter.idGenerator.UUIDGenerator;
import com.blumek.dymek.adapter.repository.AndroidSensorSettingRepository;
import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.SensorSettingRepository;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.useCase.CreateSensorSetting;
import com.blumek.dymek.useCase.CreateThermometerProfile;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;

public class CreationThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final CreateThermometerProfile createThermometerProfile;

    public CreationThermometerProfileViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ThermometerProfileRepository thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());

        SensorSettingRepository sensorSettingRepository =
                new AndroidSensorSettingRepository(appDatabase.sensorSettingsDao());

        createThermometerProfile = new CreateThermometerProfile(thermometerProfileRepository,
                new UUIDGenerator(), new ThermometerProfileValidator(new SensorSettingValidator()),
                new CreateSensorSetting(
                sensorSettingRepository,
                new UUIDGenerator(),
                new SensorSettingValidator()
        ));

        addEmptySensorSetting();
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        createThermometerProfile.create(thermometerProfile);
    }
}
