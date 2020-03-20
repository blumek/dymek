package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.adapter.idGenerator.UUIDGenerator;
import com.blumek.dymek.adapter.repository.AndroidSensorSettingRepository;
import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.application.model.ViewSensorSetting;
import com.blumek.dymek.application.model.ViewThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.useCase.CreateSensorSetting;
import com.blumek.dymek.useCase.UpdateSensorSetting;
import com.blumek.dymek.useCase.UpdateThermometerProfile;
import com.blumek.dymek.useCase.validator.SensorSettingValidator;
import com.blumek.dymek.useCase.validator.ThermometerProfileValidator;

import java.util.List;

public class UpdateThermometerProfileViewModel extends PersistenceThermometerProfileViewModel {
    private final UpdateThermometerProfile updateThermometerProfile;

    public UpdateThermometerProfileViewModel(@NonNull Application application,
                                             ThermometerProfile thermometerProfile) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ThermometerProfileRepository thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());

        updateThermometerProfile = new UpdateThermometerProfile(thermometerProfileRepository,
                new ThermometerProfileValidator(),
                new CreateSensorSetting(
                        new AndroidSensorSettingRepository(AppDatabase
                                .getInstance(application).sensorSettingsDao()),
                        new UUIDGenerator(),
                        new SensorSettingValidator()
                ),
                new UpdateSensorSetting(
                        new AndroidSensorSettingRepository(AppDatabase
                                .getInstance(application).sensorSettingsDao()),
                        new SensorSettingValidator()
                ));

        ViewThermometerProfile viewThermometerProfile = ViewThermometerProfile
                .from(thermometerProfile);

        setThermometerProfile(viewThermometerProfile);
        setSensorsSettingsList(viewThermometerProfile.getViewSensorSettings());
    }

    private void setThermometerProfile(ViewThermometerProfile viewThermometerProfile) {
        super.thermometerProfile.setValue(viewThermometerProfile);
    }

    private void setSensorsSettingsList(List<ViewSensorSetting> viewSensorSettings) {
        this.sensorsSettings.setValue(viewSensorSettings);
    }

    public void persistThermometerProfile(ThermometerProfile thermometerProfile) {
        updateThermometerProfile.update(thermometerProfile);
    }
}
