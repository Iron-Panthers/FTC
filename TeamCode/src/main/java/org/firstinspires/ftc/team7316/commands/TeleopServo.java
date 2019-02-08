package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

public class TeleopServo extends Command {
    @Override
    public void init() {

    }

    @Override
    public void loop() {
        Subsystems.instance.plateSubsystem.servoSet(OI.instance.gp2.right_stick.getY());
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
