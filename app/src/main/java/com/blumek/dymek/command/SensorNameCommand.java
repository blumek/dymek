package com.blumek.dymek.command;

public abstract class SensorNameCommand extends SensorCommand {
    private String name;

    public SensorNameCommand(int sensorId, String name) {
        super(sensorId);
        this.name = name;
    }
}
