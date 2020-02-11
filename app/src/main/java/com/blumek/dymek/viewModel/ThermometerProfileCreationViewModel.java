package com.blumek.dymek.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.fragment.ThermometerProfileFragmentDirections;
import com.blumek.dymek.model.thermometerProfile.ThermometerProfile;


public class ThermometerProfileCreationViewModel extends ThermometerProfileViewModel {
    public ThermometerProfileCreationViewModel(@NonNull Application application) {
        super(application);
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
