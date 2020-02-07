package com.blumek.dymek.viewModel;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.models.SimulationDevice;
import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.models.Thermometer;
import com.blumek.dymek.service.ThermometerService;


public class ThermometerViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Thermometer> thermometer;
    private MutableLiveData<ThermometerService.ServiceBinder> binder;
    private MutableLiveData<Device> device;

    private ServiceConnection serviceConnection;

    public ThermometerViewModel() {
        binder = new MutableLiveData<>();
        thermometer = new MutableLiveData<>();
        device = new MutableLiveData<>();

        // TODO remove static set device
        setDevice( new SimulationDevice("Dymek", "00:00:00:00:00", 3));
        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "Connected to service");
                ThermometerService.ServiceBinder serviceBinder =
                        (ThermometerService.ServiceBinder) service;
                binder.setValue(serviceBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "Disconnected from service");
                binder.setValue(null);
            }
        };
    }

    public ServiceConnection getServiceConnection(){
        return serviceConnection;
    }

    public LiveData<Thermometer> getThermometer() {
        return thermometer;
    }

    public LiveData<ThermometerService.ServiceBinder> getBinder() {
        return binder;
    }

    public void setThermometer(Thermometer thermometer) {
        Log.d(TAG, "New thermometer has been set, " + thermometer);
        this.thermometer.setValue(thermometer);
    }

    public LiveData<Sensor> getSensor(int index) {
        Thermometer thermometer = this.thermometer.getValue();
        return thermometer != null ? thermometer.getSensor(index) : new MutableLiveData<>();
    }

    public LiveData<Device> getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        Log.d(TAG, "New device has been set, " + device);
        this.device.setValue(device);
    }
}
