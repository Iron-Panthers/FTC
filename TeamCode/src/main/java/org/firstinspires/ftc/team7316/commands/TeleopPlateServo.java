package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

public class TeleopPlateServo extends Command {
    @Override
    public void init() {
        Subsystems.instance.plateSubsystem.servoRaise();
    }

    @Override
    public void loop() {
    }
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
