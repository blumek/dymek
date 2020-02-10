package com.blumek.dymek.viewModel;

import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;

import androidx.annotation.NonNull;

import com.blumek.dymek.model.device.BLEDevice;
import com.blumek.dymek.model.device.Device;
import com.blumek.dymek.scanner.BluetoothLEDeviceScanner;


public class BLEScanDevicesViewModel extends ScanDevicesViewModel {
    public BLEScanDevicesViewModel(@NonNull Application application) {
        super(application);
    }

    @Override
    BluetoothLEDeviceScanner getDeviceScanner() {
        ScanCallback scanCallback = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice bluetoothDevice = result.getDevice();
                Device device = new BLEDevice(getApplication(), bluetoothDevice);
                addDevice(device);
            }
        };
        return new BluetoothLEDeviceScanner(scanCallback);
    }
}
