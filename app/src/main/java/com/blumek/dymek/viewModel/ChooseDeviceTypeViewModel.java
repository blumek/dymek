package com.blumek.dymek.viewModel;

import android.app.Application;
import android.content.pm.PackageManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.blumek.dymek.fragment.ChooseDeviceTypeFragmentDirections;

public class ChooseDeviceTypeViewModel extends AndroidViewModel {
    public ChooseDeviceTypeViewModel(@NonNull Application application) {
        super(application);
    }

    public void onBLEDeviceClick(View view) {
        NavDirections direction = ChooseDeviceTypeFragmentDirections
                .actionChooseDeviceTypeFragmentToBLEScanDevicesFragment();
        Navigation.findNavController(view).navigate(direction);
    }

    public boolean isBLESupported() {
        return getApplication()
                .getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    public void onSimulationDeviceClick(View view) {
        NavDirections direction = ChooseDeviceTypeFragmentDirections
                .actionChooseDeviceTypeFragmentToSimulationScanDevicesFragment();
        Navigation.findNavController(view).navigate(direction);
    }
}
