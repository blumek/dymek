package com.blumek.dymek.devices.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.scan.BluetoothDeviceScanner;
import com.blumek.dymek.devices.scan.DeviceScanner;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

public class ScanDevicesViewModel extends ViewModel {
    private MutableLiveData<List<Device>> devices;
    private DeviceScanner deviceScanner;

    public ScanDevicesViewModel() {
        devices = new MutableLiveData<>();
        deviceScanner = new BluetoothDeviceScanner();
    }

    public void startScanning() {
        clearDevices();
        deviceScanner.startScanning();
    }

    public void stopScanning() {
        deviceScanner.cancelScanning();
    }

    public void addDevice(Device device) {
        List<Device> currentDevices = getDevicesValue();
        currentDevices.add(device);
        this.devices.setValue(currentDevices);
    }

    private List<Device> getDevicesValue() {
        if (devices.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(devices.getValue());
    }

    private void clearDevices() {
        this.devices.setValue(new ArrayList<Device>());
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }
}
