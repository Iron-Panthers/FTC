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
        if (OI.instance.gp2.right_bumper.pressedState()) {
            Subsystems.instance.plateSubsystem.servoRaise();
        }
        if (OI.instance.gp2.left_bumper.pressedState()) {
            Subsystems.instance.plateSubsystem.servoLower();
        }
    }
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {

    }
}
