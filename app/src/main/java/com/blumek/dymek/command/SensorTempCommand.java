package com.blumek.dymek.command;

abstract class SensorTempCommand extends SensorCommand {
    double temperature;

    SensorTempCommand(int sensorId, double temperature) {
        super(sensorId);
        this.temperature = temperature;
    }
}
