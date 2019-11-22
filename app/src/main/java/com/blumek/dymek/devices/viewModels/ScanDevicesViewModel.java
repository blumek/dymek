package com.blumek.dymek.devices.viewModels;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.devices.models.BLEDevice;
import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.scan.BluetoothLEDeviceScanner;
import com.blumek.dymek.devices.scan.DeviceScanner;
import com.google.common.collect.Lists;

import java.util.List;

public class ScanDevicesViewModel extends ViewModel {
    private MutableLiveData<List<Device>> devices;
    private DeviceScanner deviceScanner;
    private boolean isInitialRun;

    public ScanDevicesViewModel() {
        devices = new MutableLiveData<>();
        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice bluetoothDevice = result.getDevice();
                Device device = new BLEDevice(bluetoothDevice.getName(), bluetoothDevice.getAddress());
                addDevice(device);
            }
        };
        deviceScanner = new BluetoothLEDeviceScanner(scanCallback);
        isInitialRun = true;
    }

    public void startScanning() {
        clearDevices();
        deviceScanner.startScanning();
    }

    public void stopScanning() {
        deviceScanner.cancelScanning();
    }

    private void addDevice(Device device) {
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
