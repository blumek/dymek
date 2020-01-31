package com.blumek.dymek.command;

public abstract class SensorCommand implements Command{
    private int sensorId;

    public SensorCommand(int sensorId) {
        this.sensorId = sensorId;
    }
}
