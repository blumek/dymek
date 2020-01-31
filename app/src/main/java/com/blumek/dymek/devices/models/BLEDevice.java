package com.blumek.dymek.devices.models;

import android.app.Application;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.blumek.dymek.command.Command;
import com.blumek.dymek.command.DymekAuthorizationCommand;
import com.blumek.dymek.shared.exceptions.BluetoothAdapterNotAvailableException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;


public class BLEDevice extends Device {
    private final String TAG = getClass().getSimpleName();
    private static final UUID BLUETOOTH_LE_CCCD           = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");
    private static final UUID BLUETOOTH_LE_CC254X_SERVICE = UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb");
    private static final UUID BLUETOOTH_LE_CC254X_CHAR_RW = UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb");
    private static final UUID BLUETOOTH_LE_NRF_SERVICE    = UUID.fromString("6e400001-b5a3-f393-e0a9-e50e24dcca9e");
    private static final UUID BLUETOOTH_LE_NRF_CHAR_RW2   = UUID.fromString("6e400002-b5a3-f393-e0a9-e50e24dcca9e");
    private static final UUID BLUETOOTH_LE_NRF_CHAR_RW3   = UUID.fromString("6e400003-b5a3-f393-e0a9-e50e24dcca9e");
    private static final UUID BLUETOOTH_LE_RN4870_SERVICE = UUID.fromString("49535343-FE7D-4AE5-8FA9-9FAFD205E455");
    private static final UUID BLUETOOTH_LE_RN4870_CHAR_RW = UUID.fromString("49535343-1E4D-4BD9-BA61-23C647249616");
    private static final int MAX_MTU = 512;
    private static final int DEFAULT_MTU = 23;
    private int payloadSize = DEFAULT_MTU-3;

    private BluetoothGatt bluetoothGatt;
    private BluetoothDevice bluetoothDevice;
    private BluetoothGattCharacteristic readCharacteristic, writeCharacteristic;
    private final ArrayList<byte[]> writeBuffer = new ArrayList<>();
    private boolean writePending;
    private Context context;

    private final BluetoothGattCallback gattCallback =
            new BluetoothGattCallback() {
                @Override
                public void onConnectionStateChange(BluetoothGatt gatt, int status,
                                                    int newState) {
                    if (newState == BluetoothProfile.STATE_DISCONNECTED) {
                        Log.i(TAG, "Disconnected from bluetooth device");
                        setState(State.Disconnected);
                    } else if (newState == BluetoothProfile.STATE_CONNECTED) {
                        Log.i(TAG, "Connected to bluetooth device");
                        bluetoothGatt.discoverServices();
                        setState(State.Connected);
                    }
                }

                @Override
                public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                    if (status == BluetoothGatt.GATT_SUCCESS) {
                        Log.i(TAG, "Services discovered successfully");
                        getReadAndWriteCharacteristics(gatt);
                    } else
                        Log.e(TAG, "An error occurred while discovering services");
                }

                @Override
                public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                    if (new String(characteristic.getValue()).equals("[DymekHi]"))
                        send(new DymekAuthorizationCommand());
                    Log.d(TAG, new String(characteristic.getValue()));
                }

                @Override
                public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
                    super.onMtuChanged(gatt, mtu, status);
                    if(status ==  BluetoothGatt.GATT_SUCCESS) {
                        payloadSize = mtu - 3;
                    }
                    connectCharacteristics(gatt);
                }

                @Override
                public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                    if(writeCharacteristic == null)
                        return;
                    if(status != BluetoothGatt.GATT_SUCCESS) {
                        Log.e(TAG, "write failed");
                        return;
                    }
                    if(characteristic == writeCharacteristic) {
                        Log.d(TAG,"write finished, status = " + status);
                        Log.d(TAG,"Written = " + new String(characteristic.getValue()));
                        writeNext();
                    }
                }
            };

    public BLEDevice(Application application, String name, String address, int sensorCount) {
        super(name, address, sensorCount);
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null)
            throw new BluetoothAdapterNotAvailableException();

        this.context = application;
        this.bluetoothDevice = bluetoothAdapter.getRemoteDevice(address);
    }

    public BLEDevice(Application application, BluetoothDevice bluetoothDevice, int sensorCount) {
        super(bluetoothDevice.getName(), bluetoothDevice.getAddress(), sensorCount);
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
        return getState().getValue() == State.Disconnected;
    }

    @Override
    public void disconnect() {
        if (isDisconnected())
            return;

        setState(State.Disconnecting);
        bluetoothGatt.disconnect();
        bluetoothGatt = null;
    }

    public void send(Command command) {
        if(writeCharacteristic == null)
            return;
        byte[] data = command.transferValue().getBytes();
        byte[] data0;
        synchronized (writeBuffer) {
            if(data.length <= payloadSize) {
                data0 = data;
            } else {
                data0 = Arrays.copyOfRange(data, 0, payloadSize);
            }
            if(!writePending && writeBuffer.isEmpty()) {
                writePending = true;
            } else {
                writeBuffer.add(data0);
                Log.d(TAG,"write queued, len="+data0.length);
                data0 = null;
            }
            if(data.length > payloadSize) {
                for(int i=1; i<(data.length+payloadSize-1)/payloadSize; i++) {
                    int from = i*payloadSize;
                    int to = Math.min(from+payloadSize, data.length);
                    writeBuffer.add(Arrays.copyOfRange(data, from, to));
                    Log.d(TAG,"write queued, len="+(to-from));
                }
            }
        }
        if(data0 != null) {
            writeCharacteristic.setValue(data0);
            if (!bluetoothGatt.writeCharacteristic(writeCharacteristic)) {
                Log.d(TAG, "write failed");
            } else {
                Log.d(TAG,"write started, len="+data0.length);
            }
        }
    }

    private void getReadAndWriteCharacteristics(BluetoothGatt gatt) {
        for (BluetoothGattService gattService : gatt.getServices()) {
            if (gattService.getUuid().equals(BLUETOOTH_LE_CC254X_SERVICE)) {
                readCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_CC254X_CHAR_RW);
                writeCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_CC254X_CHAR_RW);
            } else if (gattService.getUuid().equals(BLUETOOTH_LE_RN4870_SERVICE)) {
                readCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_RN4870_CHAR_RW);
                writeCharacteristic = gattService.getCharacteristic(BLUETOOTH_LE_RN4870_CHAR_RW);
            } else if (gattService.getUuid().equals(BLUETOOTH_LE_NRF_SERVICE)) {
                BluetoothGattCharacteristic rw2 = gattService.getCharacteristic(BLUETOOTH_LE_NRF_CHAR_RW2);
                BluetoothGattCharacteristic rw3 = gattService.getCharacteristic(BLUETOOTH_LE_NRF_CHAR_RW3);
                if (rw2 != null && rw3 != null) {
                    int rw2prop = rw2.getProperties();
                    int rw3prop = rw3.getProperties();
                    boolean rw2write = (rw2prop & BluetoothGattCharacteristic.PROPERTY_WRITE) != 0;
                    boolean rw3write = (rw3prop & BluetoothGattCharacteristic.PROPERTY_WRITE) != 0;
                    if (rw2write && rw3write) {
                        return;
                    } else if (rw2write) {
                        writeCharacteristic = rw2;
                        readCharacteristic = rw3;
                    } else if (rw3write) {
                        writeCharacteristic = rw3;
                        readCharacteristic = rw2;
                    } else {
                        return;
                    }
                }
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            gatt.requestMtu(MAX_MTU);
        } else {
            connectCharacteristics(gatt);
        }
    }

    private void connectCharacteristics(BluetoothGatt gatt) {
        if (readCharacteristic == null || writeCharacteristic == null) {
            return;
        }

        int writeProperties = writeCharacteristic.getProperties();
        if ((writeProperties & (BluetoothGattCharacteristic.PROPERTY_WRITE +
                BluetoothGattCharacteristic.PROPERTY_WRITE_NO_RESPONSE)) ==0) {
            return;
        }

        if (!gatt.setCharacteristicNotification(readCharacteristic,true)) {
            return;
        }

        BluetoothGattDescriptor readDescriptor = readCharacteristic.getDescriptor(BLUETOOTH_LE_CCCD);
        if (readDescriptor == null) {
            return;
        }

        int readProperties = readCharacteristic.getProperties();
        if ((readProperties & BluetoothGattCharacteristic.PROPERTY_INDICATE) != 0) {
            readDescriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
        } else if((readProperties & BluetoothGattCharacteristic.PROPERTY_NOTIFY) != 0) {
            readDescriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        } else {
            return;
        }

        gatt.writeDescriptor(readDescriptor);
    }

    private void writeNext() {
        final byte[] data;
        synchronized (writeBuffer) {
            if (!writeBuffer.isEmpty()) {
                writePending = true;
                data = writeBuffer.remove(0);
            } else {
                writePending = false;
                data = null;
            }
        }
        if(data != null) {
            writeCharacteristic.setValue(data);
            if (!bluetoothGatt.writeCharacteristic(writeCharacteristic)) {
                Log.d(TAG, "write failed");
            } else {
                Log.d(TAG,"write started, len="+data.length);
            }
        }
    }
}
