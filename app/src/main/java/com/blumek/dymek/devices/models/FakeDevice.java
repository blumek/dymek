package com.blumek.dymek.devices.models;

import com.blumek.dymek.command.Command;
import com.blumek.dymek.thermometer.models.Sensor;

import java.util.Random;

public class FakeDevice extends Device {
    public FakeDevice(String name, String address, int sensorsCount) {
        super(name, address, sensorsCount);
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
