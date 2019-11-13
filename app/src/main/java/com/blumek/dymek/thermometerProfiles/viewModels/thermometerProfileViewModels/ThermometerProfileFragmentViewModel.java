package com.blumek.dymek.thermometerProfiles.viewModels.thermometerProfileViewModels;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.fragments.thermometerProfileFragments.ThermometerProfileFragmentDirections;
import com.blumek.dymek.thermometerProfiles.models.SensorSettings;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileMetadataRepositories.ThermometerProfileMetadataRepository;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileMetadataRepositories.ThermometerProfileMetadataRepositoryImpl;
import com.google.common.collect.Lists;

import java.util.Date;
import java.util.List;

public class ThermometerProfileFragmentViewModel extends AndroidViewModel {
    private LiveData<List<ThermometerProfileMetadata>> thermometerProfiles;

    public ThermometerProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ThermometerProfileMetadataRepository thermometerProfileMetadataRepository =
                new ThermometerProfileMetadataRepositoryImpl(appDatabase.thermometerProfileMetadataDao());
        thermometerProfiles = thermometerProfileMetadataRepository.getAllThermometerProfiles();
    }

    public LiveData<List<ThermometerProfileMetadata>> getThermometerProfiles() {
        return thermometerProfiles;
    }

    public void onCreateClicked(View view) {
        NavDirections directions = ThermometerProfileFragmentDirections
                .actionThermometerProfileFragmentToCreationThermometerProfileFragment();
        Navigation.findNavController(view).navigate(directions);
    }

    public void onEditClicked(View view) {
        ThermometerProfile thermometerProfile = new ThermometerProfile();
        ThermometerProfileMetadata thermometerProfileMetadata = new ThermometerProfileMetadata("Test 1", new Date());
        thermometerProfileMetadata.setId(2);
        List<SensorSettings> sensorsSettings = Lists.newArrayList(SensorSettings.emptySensorSettings());
        thermometerProfile.setMetadata(thermometerProfileMetadata);
        thermometerProfile.setSensorSettings(sensorsSettings);
        NavDirections directions = ThermometerProfileFragmentDirections
                .actionThermometerProfileFragmentToUpdateThermometerProfileFragment(thermometerProfile);
        Navigation.findNavController(view).navigate(directions);
    }
}
