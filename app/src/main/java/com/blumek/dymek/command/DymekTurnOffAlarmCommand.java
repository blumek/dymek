package com.blumek.dymek.command;

public class DymekTurnOffAlarmCommand implements Command{
    private static final String TURN_OFF_ALARM_COMMAND = "[SW11]";

    @Override
    public String transferValue() {
        return TURN_OFF_ALARM_COMMAND;
    }
}
