package com.blumek.dymek.devices.scan;

import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

public class BluetoothDeviceScanner implements DeviceScanner {
    private static final long SCAN_PERIOD = 10000;

    private final BluetoothAdapter bluetoothAdapter;
    private final Handler handler;
    private final Runnable cancelDiscoveryDelayed;
    private boolean isScanning;

    public BluetoothDeviceScanner() {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        handler = new Handler();
        cancelDiscoveryDelayed = this::cancelScanning;
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

        isScanning = false;
        bluetoothAdapter.cancelDiscovery();
        handler.removeCallbacks(cancelDiscoveryDelayed);
    }
}
