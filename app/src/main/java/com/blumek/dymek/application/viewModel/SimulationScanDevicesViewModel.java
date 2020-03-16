package com.blumek.dymek.application.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;

import com.blumek.dymek.application.fragment.SimulationScanDevicesFragmentDirections;
import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.scanner.DeviceScanner;
import com.blumek.dymek.scanner.SimulationDeviceScanner;


public class SimulationScanDevicesViewModel extends ScanDevicesViewModel {
    public SimulationScanDevicesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    DeviceScanner getDeviceScanner() {
        return new SimulationDeviceScanner(devicesUpdater);
    }

    @Override
    public NavDirections getDirection(Device device) {
        return SimulationScanDevicesFragmentDirections
                .actionSimulationScanDevicesFragmentToChooseProfileFragment();
    }
}
