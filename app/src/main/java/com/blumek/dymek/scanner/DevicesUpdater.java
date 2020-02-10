package com.blumek.dymek.scanner;

import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.model.device.Device;
import com.google.common.collect.Lists;

import java.util.List;

public class DevicesUpdater {
    private static final int DEVICE_NOT_FOUND = -1;
    private MutableLiveData<List<Device>> devices;

    public DevicesUpdater(MutableLiveData<List<Device>> devices) {
        this.devices = devices;
    }

    void addDevice(Device device) {
        List<Device> currentDevices = getDevicesValue();
        int deviceIndex = getIndexOfDeviceWithAddress(currentDevices, device.getAddress());
        if (isDeviceAbsent(deviceIndex))
            currentDevices.add(device);
        else
            currentDevices.set(deviceIndex, device);

        devices.setValue(currentDevices);
    }

    private int getIndexOfDeviceWithAddress(List<Device> devices, String address) {
        for (int index=0; index<devices.size(); index++) {
            if (address.equals(devices.get(index).getAddress()))
                return index;
        }
        return DEVICE_NOT_FOUND;
    }

    private boolean isDeviceAbsent(int deviceIndex) {
        return deviceIndex == DEVICE_NOT_FOUND;
    }

    private List<Device> getDevicesValue() {
        if (devices.getValue() == null)
            return Lists.newArrayList();
        return Lists.newArrayList(devices.getValue());
    }
}
