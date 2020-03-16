package com.blumek.dymek.application.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.application.fragment.ThermometerProfileFragmentDirections;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;
import com.blumek.dymek.useCase.DeleteThermometerProfile;


public class ThermometerProfileCreationViewModel extends ThermometerProfileViewModel {
    private final DeleteThermometerProfile deleteThermometerProfile;

    public ThermometerProfileCreationViewModel(@NonNull Application application) {
        super(application);

        deleteThermometerProfile = new DeleteThermometerProfile(thermometerProfileRepository);
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
        deleteThermometerProfile.delete(thermometerProfile);
    }
}
