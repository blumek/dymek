package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.blumek.dymek.application.model.ViewSensorSetting;
import com.blumek.dymek.application.model.ViewThermometerProfile;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.google.common.collect.Lists;

import java.util.List;

import lombok.Getter;

@Getter
public abstract class PersistenceThermometerProfileViewModel extends AndroidViewModel {
    final MutableLiveData<ViewThermometerProfile> thermometerProfile;
    final MutableLiveData<List<ViewSensorSetting>> sensorsSettings;

    PersistenceThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        thermometerProfile = new MutableLiveData<>(ViewThermometerProfile.empty());
        sensorsSettings = new MutableLiveData<>(Lists.newArrayList());
    }

    public void onAddSensorSetting() {
        addEmptySensorSetting();
    }

    void addEmptySensorSetting() {
        List<ViewSensorSetting> currentSensorsSettings = getCurrentSensorsSettings();
        currentSensorsSettings.add(ViewSensorSetting.empty());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<ViewSensorSetting> getCurrentSensorsSettings() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    public void onRemoveSensorSetting(int index) {
        List<ViewSensorSetting> currentSensorsSettings = getCurrentSensorsSettings();
        currentSensorsSettings.remove(index);
        sensorsSettings.setValue(currentSensorsSettings);
    }

    @SuppressWarnings("ConstantConditions")
    public void onPersist(View view) {
        ViewThermometerProfile viewThermometerProfile = thermometerProfile.getValue();
        viewThermometerProfile.setViewSensorSettings(sensorsSettings.getValue());
        persistThermometerProfile(viewThermometerProfile.toThermometerProfile());

        Navigation.findNavController(view).navigateUp();
    }

    public abstract void persistThermometerProfile(ThermometerProfile thermometerProfile);
}
