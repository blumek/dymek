package com.blumek.dymek.viewModel;

import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

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

    public void onDeviceClick(View view, Device device) {
        navigateToProfileSelection(view, device);
    }

    private void navigateToProfileSelection(View view, Device device) {
        Navigation.findNavController(view).navigate(getDirection(device));
    }

    public abstract NavDirections getDirection(Device device);
}
