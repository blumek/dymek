package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.blumek.dymek.thermometer.models.Thermometer;
import com.blumek.dymek.thermometer.services.ThermometerService;


public class ThermometerViewModel extends ViewModel {
    private final String TAG = getClass().getSimpleName();

    private MutableLiveData<Thermometer> thermometer;
    private ThermometerService thermometerService;

    private ServiceConnection serviceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected: connected to service");
            ThermometerService.ServiceBinder serviceBinder =
                    (ThermometerService.ServiceBinder) service;
            thermometerService = serviceBinder.getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected: disconnected from service.");
            thermometerService = null;
        }
    };

    public ThermometerViewModel() {
        this.thermometer = new MutableLiveData<>();
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
}
