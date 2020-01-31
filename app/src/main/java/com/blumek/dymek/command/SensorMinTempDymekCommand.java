package com.blumek.dymek.command;

import java.util.Locale;

public class SensorMinTempDymekCommand extends SensorTempCommand{
    private static final String SENSOR_MIN_TEMP_COMMAND = "[TMIN%d-%.2f]";

    public SensorMinTempDymekCommand(int sensorId, double temperature) {
        super(sensorId, temperature);
    }

    @Override
    public String transferValue() {
        return String.format(Locale.ENGLISH, SENSOR_MIN_TEMP_COMMAND, sensorId, temperature);
    }
}
