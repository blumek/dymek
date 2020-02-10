package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.scanner.DeviceScanner;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class ScanDevicesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Device>> devices;
    private DeviceScanner deviceScanner;
    private boolean isInitialRun;

    ScanDevicesViewModel(@NonNull Application application) {
        super(application);
        devices = new MutableLiveData<>();
        deviceScanner = getDeviceScanner();
        isInitialRun = true;
    }

    abstract DeviceScanner getDeviceScanner();

    public void startScanning() {
        clearDevices();
        deviceScanner.startScanning();
    }

    public void stopScanning() {
        deviceScanner.cancelScanning();
    }

    void addDevice(Device device) {
        List<Device> currentDevices = getDevicesValue();
        int deviceIndex = getIndexOfDeviceWithAddress(currentDevices, device.getAddress());
        if (isDeviceAbsent(deviceIndex))
            currentDevices.add(device);
        else
            currentDevices.set(deviceIndex, device);

        devices.setValue(currentDevices);
    }

    private int getIndexOfDeviceWithAddress(List<Device> devices, String address) {
        for (int i=0; i<devices.size(); i++) {
            if (address.equals(devices.get(i).getAddress()))
                return i;
        }
        return -1;
    }

    private boolean isDeviceAbsent(int deviceIndex) {
        return deviceIndex == -1;
    }

    private List<Device> getDevicesValue() {
        if (devices.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(devices.getValue());
    }

    private void clearDevices() {
        devices.setValue(Lists.newArrayList());
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }

    public boolean isInitialRun() {
        return isInitialRun;
    }

    public void initialRun() {
        isInitialRun = false;
    }
}
