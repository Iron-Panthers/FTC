package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by aaron on 11/3/18.
 */

public class ClimbForTime extends Command {

    private double power;
    private double duration;

    private ElapsedTime timer;

    public ClimbForTime(double power, double duration, ClimbDirections direction) {
        requires(Subsystems.instance.climberSubsystem);
        if (direction == ClimbDirections.DOWN) { //check direction
            this.power = Math.abs(power) * -1; //direction is 1 or -1
        } else {
            this.power = Math.abs(power);
        }
        this.duration = duration;
        timer = new ElapsedTime();
    }

    @Override
    public void init() {
        timer.reset();
        Subsystems.instance.climberSubsystem.setClimbPower(0);
    }

    @Override
    public void loop() {
        Subsystems.instance.climberSubsystem.setClimbPower(power);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= duration;
    }

    @Override
    protected void end() {
        Subsystems.instance.climberSubsystem.setClimbPower(0);
    }

    public enum ClimbDirections {
        UP,
        DOWN
    }
}