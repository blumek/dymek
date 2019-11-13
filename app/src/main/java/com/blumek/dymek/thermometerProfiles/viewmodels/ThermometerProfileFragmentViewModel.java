package com.blumek.dymek.thermometerProfiles.viewmodels;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.thermometerProfiles.fragments.ThermometerProfileFragmentDirections;
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfileMetadata;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileMetadataRepository;
import com.blumek.dymek.thermometerProfiles.repositories.ThermometerProfileMetadataRepositoryImpl;

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
}
