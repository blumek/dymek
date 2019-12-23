package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.thermometer.models.Sensor;
import com.blumek.dymek.thermometer.models.Thermometer;

import java.util.Random;

public class ThermometerViewModel extends ViewModel {
    private MutableLiveData<Thermometer> thermometer;

    public ThermometerViewModel() {
        this.thermometer = new MutableLiveData<>();

        int sensorsCount = 90;
        Thermometer thermometer = new Thermometer(sensorsCount);
        this.thermometer.setValue(thermometer);

        new Thread(() -> {
            while(true) {
                for (int i=0; i<sensorsCount; i++) {
                    thermometer.updateSensor(i, new Sensor("Sensor " + (i+1), new Random().nextInt(100)));
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public LiveData<Thermometer> getThermometer() {
        return thermometer;
    }
}
