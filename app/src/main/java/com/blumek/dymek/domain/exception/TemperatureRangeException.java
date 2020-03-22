package com.blumek.dymek.domain.exception;

public class TemperatureRangeException extends RuntimeException {
    public TemperatureRangeException() {
    }

    public TemperatureRangeException(String message) {
        super(message);
    }
}
