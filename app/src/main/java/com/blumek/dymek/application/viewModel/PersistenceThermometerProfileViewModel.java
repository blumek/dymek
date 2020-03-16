package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.domain.entity.thermometerProfile.SensorSettings;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfileMetadata;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class PersistenceThermometerProfileViewModel extends AndroidViewModel {
    final ThermometerProfileRepository thermometerProfileRepository;
    MutableLiveData<ThermometerProfileMetadata> metadata;
    MutableLiveData<List<SensorSettings>> sensorsSettings;

    PersistenceThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());
    }

    public LiveData<List<SensorSettings>> getSensorsSettings() {
        return sensorsSettings;
    }

    public LiveData<ThermometerProfileMetadata> getMetadata() {
        return metadata;
    }

    public void onAddSensorSettingsTemplate() {
        addNewEmptySensorSettingsTemplate();
    }

    void addNewEmptySensorSettingsTemplate() {
        List<SensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.add(SensorSettings.emptySensorSettings());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<SensorSettings> getSensorsSettingsValue() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    public void onRemoveSensorSettingsTemplateClick(SensorSettings sensorSettings) {
        List<SensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.remove(sensorSettings);
        sensorsSettings.setValue(currentSensorsSettings);
    }

    public void onPersistClick(View view) {
        ThermometerProfile thermometerProfile =
                new ThermometerProfile(metadata.getValue(), sensorsSettings.getValue());
        persistThermometerProfile(thermometerProfile);

        Navigation.findNavController(view).navigateUp();
    }

    public abstract void persistThermometerProfile(ThermometerProfile thermometerProfile);
}
