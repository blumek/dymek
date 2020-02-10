package com.blumek.dymek.scanner;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.model.device.SimulationDevice;
import com.google.common.collect.Lists;

import java.util.List;

public class SimulationDeviceScanner implements DeviceScanner {
    private MutableLiveData<List<Device>> devices;

    public SimulationDeviceScanner() {
        devices = new MutableLiveData<>(Lists.newArrayList());
    }

    @Override
    public void startScanning() {
        devices.setValue(Lists.newArrayList(
                new SimulationDevice("Test 1", "00:14:22:01:23:45"),
                new SimulationDevice("Test 2", "00:A0:C9:14:C8:29"),
                new SimulationDevice("Test 3", "00:1B:44:11:3A:B7"),
                new SimulationDevice("Test 4", "30:65:EC:6F:C4:58"),
                new SimulationDevice("Test 5", "21:1B:44:11:3A:B7"))
        );
    }

    @Override
    public void cancelScanning() {
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }
}
