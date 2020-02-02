package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.devices.models.Device;
import com.blumek.dymek.devices.models.SimulationDevice;
import com.blumek.dymek.thermometer.models.Thermometer;


public class ThermometerViewModel extends ViewModel {
    private MutableLiveData<Thermometer> thermometer;

    public ThermometerViewModel() {
        Device device = new SimulationDevice("", "", 3);
        this.thermometer = new MutableLiveData<>();
        setThermometer(device.getThermometer());
        device.connect();
    }

    public void setThermometer(Thermometer thermometer) {
        this.thermometer.setValue(thermometer);
    }

    public LiveData<Thermometer> getThermometer() {
        return thermometer;
    }
}
