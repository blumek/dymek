package com.blumek.dymek.command;

public abstract class SensorNameCommand extends SensorCommand {
    protected String name;

    SensorNameCommand(int sensorId, String name) {
        super(sensorId);
        this.name = name;
    }
}
