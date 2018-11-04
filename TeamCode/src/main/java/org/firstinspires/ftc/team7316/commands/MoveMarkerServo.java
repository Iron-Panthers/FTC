package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.MarkerSubsystem;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;
import com.qualcomm.robotcore.util.ElapsedTime;

//created by aaron on 11/3/2018

public class MoveMarkerServo extends Command {

    private ElapsedTime timer;

    private MarkerSubsystem.MarkerPositions position;

    @Override
    public void init() {
        timer.reset();
        Subsystems.instance.markerSubsystem.reset();
    }

    public MoveMarkerServo(MarkerSubsystem.MarkerPositions position) {
        timer = new ElapsedTime();
        Subsystems.instance.markerSubsystem.moveArm(position);
    }

    @Override
    public void loop() {

    }

    @Override
    public boolean shouldRemove() {
        return timer.seconds() >= Constants.servoMovingWaitTime; //time to wait for servo to move
    }

    @Override
    protected void end() {
    }
}
