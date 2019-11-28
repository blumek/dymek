package com.blumek.dymek.thermometer.viewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.blumek.dymek.thermometer.models.Sensor;

import java.util.ArrayList;
import java.util.List;

public class ThermometerViewModel extends ViewModel {
    private MutableLiveData<List<MutableLiveData<Sensor>>> sensors;

    public ThermometerViewModel() {
        Sensor[] vSensors = new Sensor[] {
                new Sensor("TEST 1", 10),
                new Sensor("TEST 2", 20),
                new Sensor("TEST 3", 30),
                new Sensor("TEST 4", 40),
                new Sensor("TEST 5", 50),
        };

        List<MutableLiveData<Sensor>> sensorsArray = new ArrayList<>();
        for (Sensor sensor : vSensors) {
            sensorsArray.add(new MutableLiveData<>(sensor));
        }
        sensors = new MutableLiveData<>(sensorsArray);

        new Thread(() -> {
            for (int i=0;;i++) {
                List<MutableLiveData<Sensor>> currentS = sensors.getValue();

                if (i % 2 == 0)
                    currentS.add(new MutableLiveData<>(new Sensor("JD " + i, i + i * 10)));

                for (MutableLiveData<Sensor> liveData : currentS) {
                    Sensor sensor = liveData.getValue();
                    sensor.setTemperature(sensor.getTemperature() + 10);
                    liveData.postValue(sensor);
                    System.out.println(liveData.getValue().getTemperature());
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public LiveData<List<MutableLiveData<Sensor>>> getSensors() {
        return sensors;
    }
}
