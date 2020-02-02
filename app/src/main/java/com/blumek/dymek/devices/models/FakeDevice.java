package com.blumek.dymek.devices.models;

import com.blumek.dymek.command.Command;
import com.blumek.dymek.thermometer.models.Sensor;

import java.util.Random;

public class FakeDevice extends Device {
    private Random random;

    public FakeDevice(String name, String address, int sensorsCount) {
        super(name, address, sensorsCount);
        random = new Random();
        createStartingTemperatures();

        new Thread(() -> {
            while(true) {
                simulateTemperatures();
                pauseFor(700 + random.nextInt(300));
            }
        }).start();
    }

    private void createStartingTemperatures() {
        int sensorsCount = thermometer.getSensors().length;
        for (int i=0; i<sensorsCount; i++) {
            thermometer.updateSensor(i, new Sensor("Sensor " + (i+1),
                    25 + random.nextInt(25)));
        }
    }

    private void simulateTemperatures() {
        int sensorsCount = thermometer.getSensors().length;
        for (int i=0; i<sensorsCount; i++) {
            double oldTemp = thermometer.getSensor(i).getValue() != null ?
                    thermometer.getSensor(i).getValue().getTemperature() : 0;
            thermometer.updateSensor(i, new Sensor("Sensor " + (i+1),
                    oldTemp+ (random.nextDouble() - 0.5)));
        }
    }

    private void pauseFor(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() {
        state.setValue(State.Connected);
    }

    @Override
    public void disconnect() {
        state.setValue(State.Disconnected);
    }

    @Override
    public void send(Command command) {

    }
}
