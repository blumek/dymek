package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.thermometer.models.Sensor;

public class SensorViewModel extends ViewModel {
    private LiveData<Sensor> sensor;

    public SensorViewModel() {
    }

    public LiveData<Sensor> getSensor() {
        return sensor;
    }

    public void setSensor(LiveData<Sensor> sensor) {
        this.sensor = sensor;
    }
}
