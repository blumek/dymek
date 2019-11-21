package com.blumek.dymek.devices.scan;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

public class BluetoothDeviceScanner implements DeviceScanner {
    private BluetoothAdapter bluetoothAdapter;
    private boolean isScanning;
    private Handler handler;

    private static final long SCAN_PERIOD = 10000;

    public BluetoothDeviceScanner() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        handler = new Handler();
        isScanning = false;
    }

    @Override
    public void startScanning() {
        if (isScanning)
            cancelScanning();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                isScanning = false;
                bluetoothAdapter.cancelDiscovery();
            }
        }, SCAN_PERIOD);

        isScanning = true;
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public void cancelScanning() {
        if (!isScanning)
            return;

        isScanning = false;
        bluetoothAdapter.cancelDiscovery();
    }
}
