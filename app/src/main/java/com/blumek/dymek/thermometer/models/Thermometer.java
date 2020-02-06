package com.blumek.dymek.thermometer.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


public class Thermometer {
    private MutableLiveData<Sensor>[] sensors;

    public Thermometer(int sensorsCount) {
        sensors = new MutableLiveData[sensorsCount];
        for (int i = 0; i < sensorsCount; i++) {
            sensors[i] = new MutableLiveData<>(Sensor.empty());
        }
    }

    public void updateSensor(int index, Sensor sensor) {
        if (index > sensors.length)
            return;

        if (sensor == null)
            return;

        Sensor currentSensor = sensors[index].getValue() != null ? sensors[index].getValue() : Sensor.empty();
        if (isNewName(currentSensor.getName(), sensor.getName())) {
            currentSensor.setName(sensor.getName());
        }

        if (isNewTemperature(currentSensor.getTemperature(), sensor.getTemperature())) {
            currentSensor.setTemperature(sensor.getTemperature());
        }

        // TODO setValue for main thread work
        sensors[index].postValue(currentSensor);
    }

    private boolean isNewName(String oldName, String newName) {
        return newName != null && !newName.equals(oldName);
    }

    private boolean isNewTemperature(double oldTemperature, double newTemperature) {
        return oldTemperature != newTemperature;
    }

    public LiveData<Sensor>[] getSensors() {
        return sensors;
    }

    public LiveData<Sensor> getSensor(int index) {
        if (index < 0 || index >= sensors.length)
            return null;

        return sensors[index];
    }
}
