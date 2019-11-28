package com.blumek.dymek.thermometer.viewModels.factories;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.viewModels.SensorViewModel;

public class SensorViewModelFactory implements ViewModelProvider.Factory {
    private LiveData<Sensor> sensor;

    public SensorViewModelFactory(LiveData<Sensor> sensor) {
        this.sensor = sensor;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SensorViewModel(sensor);
    }
}
