package com.blumek.dymek.devices.scan;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

public class BluetoothDeviceScanner implements DeviceScanner {
    private static final long SCAN_PERIOD = 10000;

    private BluetoothAdapter bluetoothAdapter;
    private boolean isScanning;
    private Handler handler;
    private Runnable cancelDiscoveryDelayed = new Runnable() {
        @Override
        public void run() {
            isScanning = false;
            bluetoothAdapter.cancelDiscovery();
        }
    };

    public BluetoothDeviceScanner() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        handler = new Handler();
        isScanning = false;
    }

    @Override
    public void startScanning() {
        if (isScanning)
            cancelScanning();

        handler.postDelayed(cancelDiscoveryDelayed, SCAN_PERIOD);

        isScanning = true;
        bluetoothAdapter.startDiscovery();
    }

    @Override
    public void cancelScanning() {
        if (!isScanning)
            return;

        handler.removeCallbacks(cancelDiscoveryDelayed);
        isScanning = false;
        bluetoothAdapter.cancelDiscovery();
    }
}
