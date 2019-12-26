package com.blumek.dymek.devices.models;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.blumek.dymek.devices.models.commands.Command;
import com.blumek.dymek.thermometer.models.Thermometer;

import java.util.Objects;

public abstract class Device {
    String name;
    String address;
    Thermometer thermometer;
    MutableLiveData<State> state;

    Device(String name, String address) {
        this.name = name;
        this.address = address;
    }

    Device(String name, String address, int sensorsCount) {
        this.name = name;
        this.address = address;
        this.thermometer = new Thermometer(sensorsCount);
        this.state = new MutableLiveData<>(State.Disconnected);
    }

    public abstract void connect();
    public abstract void disconnect();
    public abstract void send(Command command);

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
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

    void setState(State state) {
        this.state.postValue(state);
    }

    State getRawState() {
        return this.state.getValue();
    }

    public LiveData<State> getState() {
        return state;
    }

    public Thermometer getThermometer() {
        return thermometer;
    }

    public enum State {
        Disconnected,
        Connecting,
        Connected,
        Disconnecting
    }
}
