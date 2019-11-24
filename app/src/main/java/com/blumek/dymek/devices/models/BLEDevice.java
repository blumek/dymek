package com.blumek.dymek.devices.models;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothProfile;
import android.content.Context;

import com.blumek.dymek.shared.exceptions.BluetoothAdapterNotAvailableException;

public class BLEDevice extends Device {
    private final String TAG = getClass().getSimpleName();
    private BluetoothGatt bluetoothGatt;
    private BluetoothDevice bluetoothDevice;
    private Context context;

    private final BluetoothGattCallback gattCallback =
            new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                    int newState) {
                    if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        setState(State.Disconnected);
                    } else if (newState == BluetoothProfile.STATE_CONNECTED) {
                        setState(State.Connected);
                    }
                }
            };

    public BLEDevice(Application application, String name, String address) {
        super(name, address);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null)
            throw new BluetoothAdapterNotAvailableException();

        this.context = application;
        this.bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
    }

    public BLEDevice(Application application, BluetoothDevice bluetoothDevice) {
        super(bluetoothDevice.getName(), bluetoothDevice.getAddress());
        this.context = application;
        this.bluetoothDevice = bluetoothDevice;
    }

    @Override
    public void connect() {
        if (!isDisconnected())
            return;

        bluetoothGatt = bluetoothDevice.connectGatt(context, false, gattCallback);
        setState(State.Connecting);
    }

    private boolean isDisconnected() {
        return getRawState() == State.Disconnected;
    }

    @Override
    public void disconnect() {
        if (isDisconnected())
            return;

        bluetoothGatt.disconnect();
        bluetoothGatt = null;
    }
}
