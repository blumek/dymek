package com.blumek.dymek.thermometerProfiles.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.google.common.collect.Lists;

import java.util.List;

public class CreationThermometerProfileFragmentViewModel extends AndroidViewModel {
    private MutableLiveData<ThermometerProfileMetadata> metadata;
    private MutableLiveData<List<SensorSettings>> sensorsSettings;

    public CreationThermometerProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        setEmptyMetadata();
        setEmptySensorsSettingsList();
        addNewEmptyTemplate();
    }

    public LiveData<List<SensorSettings>> getSensorsSettings() {
        return sensorsSettings;
    }

    public LiveData<ThermometerProfileMetadata> getMetadata() {
        return metadata;
    }

    public void addNewEmptyTemplate() {
        List<SensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.add(SensorSettings.emptySensorSettings());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<SensorSettings> getSensorsSettingsValue() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    private void setEmptyMetadata() {
        metadata = new MutableLiveData<>(ThermometerProfileMetadata.emptyThermometerProfileMetadata());
    }

    private void setEmptySensorsSettingsList() {
        List<SensorSettings> emptySensorsSettings = Lists.newArrayList();
        sensorsSettings = new MutableLiveData<>(emptySensorsSettings);
    }
}
