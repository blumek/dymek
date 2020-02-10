package com.blumek.dymek.viewModel;

import android.view.View;

import androidx.lifecycle.ViewModel;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.fragment.ChooseDeviceTypeFragmentDirections;

public class ChooseDeviceTypeViewModel extends ViewModel {
    public void onBLEDeviceClick(View view) {
        NavDirections direction = ChooseDeviceTypeFragmentDirections
                .actionChooseDeviceTypeFragmentToBLEScanDevicesFragment();
        Navigation.findNavController(view).navigate(direction);
    }
}
