package com.blumek.dymek.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.scanner.DeviceScanner;
import com.blumek.dymek.scanner.DevicesUpdater;
import com.google.common.collect.Lists;

import java.util.List;

public abstract class ScanDevicesViewModel extends AndroidViewModel {
    private MutableLiveData<List<Device>> devices;
    private DeviceScanner deviceScanner;
    private boolean isInitialRun;
    DevicesUpdater devicesUpdater;

    ScanDevicesViewModel(@NonNull Application application) {
        super(application);
        devices = new MutableLiveData<>();
        devicesUpdater = new DevicesUpdater(devices);
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
