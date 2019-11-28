package com.blumek.dymek.devices.models.commands;

public class AuthorizeCommand implements Command {
    @Override
    public String value() {
        return "[DymekAppHi]";
    }
}
