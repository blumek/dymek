package com.blumek.dymek.devices.models;

import java.util.Objects;

public abstract class Device {
    private String name;
    private String address;

    public Device(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public abstract void connect();
    public abstract void disconnect();

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Device device = (Device) o;
        return Objects.equals(name, device.name) &&
                Objects.equals(address, device.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, address);
    }
}
