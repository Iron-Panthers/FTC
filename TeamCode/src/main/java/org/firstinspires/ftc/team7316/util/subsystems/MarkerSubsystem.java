package org.firstinspires.ftc.team7316.util.subsystems;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.Command;
//Created by Aaron on 11/3/18

public class MarkerSubsystem extends Subsystem {

    private Servo arm;

    public MarkerSubsystem() {
        arm = Hardware.instance.markerServo;
    }

    @Override
    public void reset() {
        moveArm(MarkerPositions.INITIAL);
    }

    public void moveArm(MarkerPositions position) {
        if(position == MarkerPositions.UP) {
            arm.setPosition(Constants.markerUpSetpoint);
        }
        else if (position == MarkerPositions.DOWN){
            arm.setPosition(Constants.markerDownSetpoint);
        } else if (position == MarkerPositions.HALFDOWN) {
            arm.setPosition(Constants.markerDownSetpoint/2); //half-down for the points in auto
        } else {
            arm.setPosition(Constants.markerInitialSetpoint);
        }
    }


    @Override
    public Command defaultAutoCommand() {
        return null;
    }
    public void markerDown(){
        moveArm(MarkerPositions.DOWN);
    }
    public void markerUp() {
        moveArm(MarkerPositions.UP);
    }

    @Override
    public Command defaultTeleopCommand() {
        return null;
    }

    public enum MarkerPositions {
        UP,
        DOWN,
        HALFDOWN,
        INITIAL
    }
}
