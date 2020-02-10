package com.blumek.dymek.scanner;

public abstract class DeviceScanner {
    DevicesUpdater devicesUpdater;

    DeviceScanner(DevicesUpdater devicesUpdater) {
        this.devicesUpdater = devicesUpdater;
    }

    public abstract void startScanning();
    public abstract void cancelScanning();
}
