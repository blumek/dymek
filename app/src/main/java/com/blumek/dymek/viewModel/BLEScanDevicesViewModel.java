package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.blumek.dymek.fragment.BLEScanDevicesFragmentDirections;
import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.scanner.BluetoothLEDeviceScanner;


public class BLEScanDevicesViewModel extends ScanDevicesViewModel {
    public BLEScanDevicesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    BluetoothLEDeviceScanner getDeviceScanner() {
        return new BluetoothLEDeviceScanner(getApplication(), devicesUpdater);
    }

    @Override
    public NavDirections getDirection(Device device) {
        return BLEScanDevicesFragmentDirections
                .actionBLEScanDevicesFragmentToChooseProfileFragment();
    }
}
