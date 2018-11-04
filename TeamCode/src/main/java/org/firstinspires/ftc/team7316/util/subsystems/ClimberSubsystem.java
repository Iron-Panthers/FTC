package org.firstinspires.ftc.team7316.util.subsystems;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.commands.TeleopClimb;
import org.firstinspires.ftc.team7316.commands.ClimbForTime;

//Creates by Aaron on 11/3/18
public class ClimberSubsystem extends Subsystem {
    private boolean climbStopped;
    @Override
    public void reset() {
        Hardware.instance.climbmotor.setPower(0);
    }

    @Override
    public Command defaultAutoCommand() {
        return null;
        //return new ClimbForTime(1, 5, ClimbForTime.ClimbDirections.UP);
    }

    @Override
    public Command defaultTeleopCommand() {
        return new TeleopClimb();
    }
    public void setClimbPower(double power){
        if (!climbStopped) {
            Hardware.instance.climbmotor.setPower(power);
        }
    }

    public boolean getClimbStopped() {
        return climbStopped;
    }
    public void setClimbStopped(boolean stopped) {
        climbStopped = stopped;
    }
}
