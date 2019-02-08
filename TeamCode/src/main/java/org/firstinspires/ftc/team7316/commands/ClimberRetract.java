package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

public class ClimberRetract extends Command {
    long startTime;
    @Override

    public void init() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        Subsystems.instance.climberSubsystem.setMotor(-1);
    }

    @Override
    public boolean shouldRemove() {
        return System.currentTimeMillis()-startTime>3500;
    }

    @Override
    protected void end() {

    }
}
