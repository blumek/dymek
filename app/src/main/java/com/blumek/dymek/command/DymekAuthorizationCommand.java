package com.blumek.dymek.command;

public class DymekAuthorizationCommand implements Command {
    private static final String AUTHORIZATION_COMMAND = "[DymekAppHi]";

    @Override
    public String transferValue() {
        return AUTHORIZATION_COMMAND;
    }
}
