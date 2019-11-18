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
import com.blumek.dymek.thermometerProfiles.models.ThermometerProfile;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepository;
import com.blumek.dymek.thermometerProfiles.repositories.thermometerProfileRepositories.ThermometerProfileRepositoryImpl;

import java.util.List;

public class ThermometerProfileFragmentViewModel extends AndroidViewModel {
    private LiveData<List<ThermometerProfile>> thermometerProfiles;

    public ThermometerProfileFragmentViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ThermometerProfileRepository thermometerProfileRepository =
                new ThermometerProfileRepositoryImpl(appDatabase.thermometerProfileDao());
        thermometerProfiles = thermometerProfileRepository.getAllThermometerProfiles();
    }

    public LiveData<List<ThermometerProfile>> getThermometerProfiles() {
        return thermometerProfiles;
    }

    public void onCreateClicked(View view) {
        NavDirections directions = ThermometerProfileFragmentDirections
                .actionThermometerProfileFragmentToCreationThermometerProfileFragment();
        Navigation.findNavController(view).navigate(directions);
    }

    public void onEditClicked(View view, ThermometerProfile thermometerProfile) {
        NavDirections directions = ThermometerProfileFragmentDirections
                .actionThermometerProfileFragmentToUpdateThermometerProfileFragment(thermometerProfile);
        Navigation.findNavController(view).navigate(directions);
    }
}
