package com.blumek.dymek.thermometer.viewModels;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.models.Thermometer;
import com.blumek.dymek.thermometer.services.ThermometerService;


public class ThermometerViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Thermometer> thermometer;
    private MutableLiveData<ThermometerService.ServiceBinder> binder;

    private ServiceConnection serviceConnection;

    public ThermometerViewModel() {
        binder = new MutableLiveData<>();
        thermometer = new MutableLiveData<>();

        serviceConnection = new ServiceConnection() {

            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(TAG, "onServiceConnected: connected to service");
                ThermometerService.ServiceBinder serviceBinder =
                        (ThermometerService.ServiceBinder) service;
                binder.setValue(serviceBinder);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(TAG, "onServiceDisconnected: disconnected from service.");
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
        this.thermometer.setValue(thermometer);
    }

    public LiveData<Sensor> getSensor(int index) {
        Thermometer thermometer = this.thermometer.getValue();
        return thermometer != null ? thermometer.getSensor(index) : null;
    }
}
