package com.blumek.dymek.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.fragment.ThermometerProfileFragmentDirections;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.repository.ThermometerProfileRepository;
import com.blumek.dymek.repository.ThermometerProfileRepositoryImpl;
import com.blumek.dymek.shared.AppDatabase;

import java.util.List;

public class ThermometerProfileViewModel extends AndroidViewModel {
    private ThermometerProfileRepository thermometerProfileRepository;
    private LiveData<List<ThermometerProfile>> thermometersProfiles;

    public ThermometerProfileViewModel(@NonNull Application application) {
        super(application);
        AppDatabase appDatabase = AppDatabase.getInstance(application);
        thermometerProfileRepository =
                new ThermometerProfileRepositoryImpl(appDatabase.thermometerProfileDao());
        thermometersProfiles = thermometerProfileRepository.getAllThermometerProfiles();
    }

    public LiveData<List<ThermometerProfile>> getThermometersProfiles() {
        return thermometersProfiles;
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

    public void onDeleteClicked(ThermometerProfile thermometerProfile) {
        thermometerProfileRepository.delete(thermometerProfile);
    }
}