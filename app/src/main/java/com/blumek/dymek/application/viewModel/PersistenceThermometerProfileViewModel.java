package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomSensorSettings;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfile;
import com.blumek.dymek.adapter.repository.model.thermometerProfile.RoomThermometerProfileMetadata;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class PersistenceThermometerProfileViewModel extends AndroidViewModel {
    final ThermometerProfileRepository thermometerProfileRepository;
    MutableLiveData<RoomThermometerProfileMetadata> metadata;
    MutableLiveData<List<RoomSensorSettings>> sensorsSettings;
    // TODO CHANGE ROOM TO NORMAL

    PersistenceThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());
    }

    public LiveData<List<RoomSensorSettings>> getSensorsSettings() {
        return sensorsSettings;
    }

    public LiveData<RoomThermometerProfileMetadata> getMetadata() {
        return metadata;
    }

    public void onAddSensorSettingsTemplate() {
        addNewEmptySensorSettingsTemplate();
    }

    void addNewEmptySensorSettingsTemplate() {
        List<RoomSensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.add(RoomSensorSettings.emptySensorSettings());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<RoomSensorSettings> getSensorsSettingsValue() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    public void onRemoveSensorSettingsTemplateClick(RoomSensorSettings roomSensorSettings) {
        List<RoomSensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.remove(roomSensorSettings);
        sensorsSettings.setValue(currentSensorsSettings);
    }

    public void onPersistClick(View view) {
        RoomThermometerProfile roomThermometerProfile =
                new RoomThermometerProfile(metadata.getValue(), sensorsSettings.getValue());
        persistThermometerProfile(roomThermometerProfile.toThermometerProfile());

        Navigation.findNavController(view).navigateUp();
    }

    public abstract void persistThermometerProfile(ThermometerProfile thermometerProfile);
}
