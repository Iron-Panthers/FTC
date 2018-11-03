package org.firstinspires.ftc.team7316.util.subsystems;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
//Created by Aaron on 11/3/18

public class MarkerSubsystem extends Subsystem {

    public final double armPositionUp = 1;
    public final double armPositionDown = 0.02;

    private Servo arm;

    public MarkerSubsystem() {
        arm = Hardware.instance.markerServo;
    }

    @Override
    public void reset() {
        moveArm(MarkerPositions.UP);
    }

    public void moveArm(MarkerPositions position) {
        if(position == MarkerPositions.UP) {
            arm.setPosition(armPositionUp);
        }
        else {
            arm.setPosition(armPositionDown);
        }
    }


    @Override
    public Command defaultAutoCommand() {
        return null;
    }
    public void servoLower(){
        moveArm(MarkerPositions.DOWN);
    }
    public void servoHigher() {
        moveArm(MarkerPositions.UP);
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }

    public enum MarkerPositions {
        UP,
        DOWN
    }
}
