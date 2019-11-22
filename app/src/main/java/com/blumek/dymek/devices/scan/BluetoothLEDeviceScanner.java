package com.blumek.dymek.devices.scan;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.os.Handler;

public class BluetoothLEDeviceScanner implements DeviceScanner {
    private static final long SCAN_PERIOD = 10000;

    private final BluetoothLeScanner bluetoothLeScanner;
    private final Handler handler;
    private final Runnable cancelDiscoveryDelayed;
    private final ScanCallback callback;
    private boolean isScanning;

    public BluetoothLEDeviceScanner(ScanCallback scanCallback) {
        this.callback = scanCallback;
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothLeScanner = bluetoothAdapter.getBluetoothLeScanner();
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
        bluetoothLeScanner.startScan(callback);
    }

    @Override
    public void cancelScanning() {
        if (!isScanning)
            return;

        isScanning = false;
        bluetoothLeScanner.stopScan(callback);
        handler.removeCallbacks(cancelDiscoveryDelayed);
    }
}
