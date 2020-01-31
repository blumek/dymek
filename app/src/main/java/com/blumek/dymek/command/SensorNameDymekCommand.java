package com.blumek.dymek.command;

import java.util.Locale;

public class SensorNameDymekCommand extends SensorNameCommand {
    private static final String SENSOR_TEMP_NAME_COMMAND = "[TN%d-%s]";

    public SensorNameDymekCommand(int sensorId, String name) {
        super(sensorId, name);
    }

    @Override
    public String transferValue() {
        return String.format(Locale.ENGLISH, SENSOR_TEMP_NAME_COMMAND, sensorId, name);
    }
}
