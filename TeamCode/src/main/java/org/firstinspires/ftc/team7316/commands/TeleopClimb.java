package org.firstinspires.ftc.team7316.commands;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.input.OI;

public class TeleopClimb extends Command {
    @Override
    public void init() {
        Subsystems.instance.climberSubsystem.setClimbPower(0);
    }

    @Override
    public void loop() {
        if(OI.instance.gp2.dp_down.pressedState()) {
            Subsystems.instance.climberSubsystem.setClimbPower(-1);
        }
        else if(OI.instance.gp2.dp_up.pressedState()) {
            Subsystems.instance.climberSubsystem.setClimbPower(1);
        }
        else{
            Subsystems.instance.climberSubsystem.setClimbPower(0);
        }
    }

    @Override
    public boolean shouldRemove() {
        return false;
    }

    @Override
    protected void end() {
        Subsystems.instance.climberSubsystem.setClimbPower(0);
    }
}
