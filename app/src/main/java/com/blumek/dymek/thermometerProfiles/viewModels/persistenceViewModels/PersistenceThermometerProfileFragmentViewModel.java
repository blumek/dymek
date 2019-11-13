package com.blumek.dymek.thermometerProfiles.viewModels.persistenceViewModels;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.Navigation;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepositoryImpl;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class PersistenceThermometerProfileFragmentViewModel extends AndroidViewModel {
    ThermometerProfileRepository thermometerProfileRepository;
    MutableLiveData<ThermometerProfileMetadata> metadata;
    MutableLiveData<List<SensorSettings>> sensorsSettings;

    PersistenceThermometerProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        thermometerProfileRepository =
                new ThermometerProfileRepositoryImpl(AppDatabase.getInstance(application)
                        .thermometerProfileDao());
    }

    public LiveData<List<SensorSettings>> getSensorsSettings() {
        return sensorsSettings;
    }

    public LiveData<ThermometerProfileMetadata> getMetadata() {
        return metadata;
    }

    public void addNewEmptySensorSettingsTemplate() {
        List<SensorSettings> currentSensorsSettings = getSensorsSettingsValue();
        currentSensorsSettings.add(SensorSettings.emptySensorSettings());
        sensorsSettings.setValue(currentSensorsSettings);
    }

    private List<SensorSettings> getSensorsSettingsValue() {
        if (sensorsSettings.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(sensorsSettings.getValue());
    }

    public void onPersistClick(View view) {
        ThermometerProfile thermometerProfile =
                new ThermometerProfile(metadata.getValue(), sensorsSettings.getValue());
        persistThermometerProfile(thermometerProfile);

        Navigation.findNavController(view).navigateUp();
    }

    public abstract void persistThermometerProfile(ThermometerProfile thermometerProfile);
}
