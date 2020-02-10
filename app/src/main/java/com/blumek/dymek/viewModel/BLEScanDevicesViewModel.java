package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.scanner.BluetoothLEDeviceScanner;


public class BLEScanDevicesViewModel extends ScanDevicesViewModel {
    public BLEScanDevicesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    BluetoothLEDeviceScanner getDeviceScanner() {
        return new BluetoothLEDeviceScanner(getApplication(), devicesUpdater);
    }
}
