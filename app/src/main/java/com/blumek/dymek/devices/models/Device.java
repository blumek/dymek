package com.blumek.dymek.devices.models;

public abstract class Device {
    private String name;

    public Device(String name) {
        this.name = name;
    }

    public abstract void connect();
    public abstract void disconnect();

    public String getName() {
        return name;
    }
}
