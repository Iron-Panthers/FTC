package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.commands.TeleopClimb;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;

public class ClimberSubsystem extends Subsystem {
    @Override
    public void reset() {
        Hardware.instance.climbmotor1.setPower(0);
        Hardware.instance.climbmotor2.setPower(0);
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopClimb();
    }
    public void setMotor(double power){
        Hardware.instance.climbmotor1.setPower(power);
        Hardware.instance.climbmotor2.setPower(power);
    }

    public long currentPosition() {
        return Hardware.instance.climbmotor1.getCurrentPosition();
    }

}
