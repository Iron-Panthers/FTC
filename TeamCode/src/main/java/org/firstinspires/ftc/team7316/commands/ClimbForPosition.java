package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

public class ClimbForPosition extends Command {

    double movePower = -1;
    long ticksGoal;
    double direction = 1;
    long startTime;

    public ClimbForPosition(long ticksGoal) {
//        requires(Subsystems.instance.climberSubsystem);
        this.ticksGoal = ticksGoal;
    }

    @Override
    public void init() {
        if (ticksGoal < Subsystems.instance.climberSubsystem.currentPosition()) {
            direction = -1;
        }
        Subsystems.instance.climberSubsystem.setMotor(-.5);
        startTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        if(Hardware.instance.climberLimit.isPressed()) {
            Subsystems.instance.climberSubsystem.setMotor(0);
        }
        else {
            Subsystems.instance.climberSubsystem.setMotor(direction * movePower);
        }
    }

    @Override
    public boolean shouldRemove() {
        return Hardware.instance.climberLimit.isPressed()|| System.currentTimeMillis()-startTime>3500;
    }

    @Override
    protected void end() {
        Subsystems.instance.climberSubsystem.setMotor(0);
    }
}
