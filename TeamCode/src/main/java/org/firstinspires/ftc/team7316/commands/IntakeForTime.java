package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.team7316.util.commands.Command;

/**
 * Created by aaron on 11/3/18.
 */

public class IntakeForTime extends Command {

    private double power;
    private double duration;

    private ElapsedTime timer;

    public IntakeForTime(double power, double duration, IntakeDirections direction) {
        requires(Subsystems.instance.intakeSubsystem);
        if (direction == IntakeDirections.IN) { //check direction
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
        Subsystems.instance.intakeSubsystem.setIntakePower(0);
    }

    @Override
    public void loop() {
        Subsystems.instance.intakeSubsystem.setIntakePower(power);
    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= duration;
    }

    @Override
    protected void end() {
        Subsystems.instance.intakeSubsystem.setIntakePower(0);
    }

    public enum IntakeDirections {
        IN,
        OUT
    }
}