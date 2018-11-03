package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

//created by aaron on 11/3/2018

public class MarkerServo extends Command {
    @Override
    public void init() {
        Subsystems.instance.markerSubsystem.reset();
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return true;
    }

    @Override
    protected void end() {

    }
}
