package com.blumek.dymek.command;

abstract class SensorCommand implements Command{
    int sensorId;

    SensorCommand(int sensorId) {
        this.sensorId = sensorId;
    }
}
