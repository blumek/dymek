package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.models.SimulationDevice;
import com.blumek.dymek.thermometer.models.Thermometer;
import com.blumek.dymek.thermometer.services.ThermometerService;


public class ThermometerViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Thermometer> thermometer;
    private Device device;
    private MutableLiveData<ThermometerService.ServiceBinder> binder;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: connected to service");
            ThermometerService.ServiceBinder serviceBinder =
                    (ThermometerService.ServiceBinder) service;
            binder.postValue(serviceBinder);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: disconnected from service.");
            binder.postValue(null);
        }
    };

    public ThermometerViewModel() {
        this.thermometer = new MutableLiveData<>();
        this.binder = new MutableLiveData<>();

        device = new SimulationDevice(null, null, 3);
        device.connect();
        thermometer.postValue(device.getThermometer());
    }

    public ServiceConnection getServiceConnection(){
        return serviceConnection;
    }

    public void setThermometer(Thermometer thermometer) {
        this.thermometer.setValue(thermometer);
    }

    public LiveData<Thermometer> getThermometer() {
        return thermometer;
    }

    public LiveData<ThermometerService.ServiceBinder> getBinder() {
        return binder;
    }

    public Device getDevice() {
        return device;
    }
}
