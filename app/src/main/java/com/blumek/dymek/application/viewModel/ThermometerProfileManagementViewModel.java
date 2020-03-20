package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.adapter.repository.AndroidThermometerProfileRepository;
import com.blumek.dymek.application.fragment.ThermometerProfileManagementFragmentDirections;
import com.blumek.dymek.domain.entity.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.domain.port.ThermometerProfileRepository;
import com.blumek.dymek.shared.AppDatabase;
import com.blumek.dymek.useCase.DeleteThermometerProfile;


public class ThermometerProfileManagementViewModel extends ThermometerProfileViewModel {
    private final DeleteThermometerProfile deleteThermometerProfile;

    public ThermometerProfileManagementViewModel(@NonNull Application application) {
        super(application);

        AppDatabase appDatabase = AppDatabase.getInstance(application);
        ThermometerProfileRepository thermometerProfileRepository =
                new AndroidThermometerProfileRepository(appDatabase.thermometerProfileDao());

        deleteThermometerProfile = new DeleteThermometerProfile(thermometerProfileRepository);
    }

    public void onThermometerProfileCreate(View view) {
        NavDirections directions = ThermometerProfileManagementFragmentDirections
                .actionThermometerProfileFragmentToCreationThermometerProfileFragment();

        Navigation.findNavController(view).navigate(directions);
    }

    public void onThermometerProfileEdit(View view, ThermometerProfile thermometerProfile) {
        NavDirections directions = ThermometerProfileManagementFragmentDirections
                .actionThermometerProfileFragmentToUpdateThermometerProfileFragment(thermometerProfile);
        Navigation.findNavController(view).navigate(directions);
    }

    public void onThermometerProfileDelete(ThermometerProfile thermometerProfile) {
        deleteThermometerProfile.delete(thermometerProfile);
    }
}
