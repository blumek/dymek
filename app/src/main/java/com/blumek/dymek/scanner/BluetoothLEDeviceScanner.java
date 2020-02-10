package com.blumek.dymek.scanner;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.os.Handler;

import com.blumek.dymek.model.device.BLEDevice;
import com.blumek.dymek.model.device.Device;

public class BluetoothLEDeviceScanner extends DeviceScanner {
    private static final long SCAN_PERIOD = 10000;

    private final BluetoothLeScanner bluetoothLeScanner;
    private final Handler handler;
    private final Runnable cancelDiscoveryDelayed;
    private final ScanCallback callback;
    private boolean isScanning;

    public BluetoothLEDeviceScanner(Application application, DevicesUpdater devicesUpdater) {
        super(devicesUpdater);
        this.callback  = new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                BluetoothDevice bluetoothDevice = result.getDevice();
                Device device = new BLEDevice(application, bluetoothDevice);
                devicesUpdater.addDevice(device);
            }
        };
        // TODO ASK FOR ENABLE BLUETOOTH
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
