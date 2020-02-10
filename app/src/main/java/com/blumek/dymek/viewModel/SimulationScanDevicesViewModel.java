package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;

import com.blumek.dymek.scanner.DeviceScanner;
import com.blumek.dymek.scanner.SimulationDeviceScanner;


public class SimulationScanDevicesViewModel extends ScanDevicesViewModel {
    public SimulationScanDevicesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    DeviceScanner getDeviceScanner() {
        return new SimulationDeviceScanner();
    }
}
