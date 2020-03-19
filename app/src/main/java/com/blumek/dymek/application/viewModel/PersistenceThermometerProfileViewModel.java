package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.application.model.ViewSensorSetting;
import com.blumek.dymek.application.model.ViewThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class PersistenceThermometerProfileViewModel extends AndroidViewModel {
    final ThermometerProfileRepository thermometerProfileRepository;
    MutableLiveData<ViewThermometerProfile> thermometerProfile;
    MutableLiveData<List<ViewSensorSetting>> sensorsSettings;

    PersistenceThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());
    }

    public LiveData<List<ViewSensorSetting>> getSensorsSettings() {
        return sensorsSettings;
    }

    public LiveData<ViewThermometerProfile> getThermometerProfile() {
        return thermometerProfile;
    }

    public void onAddSensorSettingsTemplate() {
        addNewEmptySensorSettingsTemplate();
    }

    void addNewEmptySensorSettingsTemplate() {
        List<ViewSensorSetting> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.add(new ViewSensorSetting());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<ViewSensorSetting> getSensorsSettingsValue() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    public void onRemoveSensorSettingsTemplateClick(ViewSensorSetting viewSensorSetting) {
        List<ViewSensorSetting> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.remove(viewSensorSetting);
        sensorsSettings.setValue(currentSensorsSettings);
    }

    public void onPersistClick(View view) {
        ViewThermometerProfile viewThermometerProfile = thermometerProfile.getValue();
        viewThermometerProfile.setViewSensorSettings(sensorsSettings.getValue());
        persistThermometerProfile(viewThermometerProfile.toThermometerProfile());

        Navigation.findNavController(view).navigateUp();
    }

    public abstract void persistThermometerProfile(ThermometerProfile thermometerProfile);
}
