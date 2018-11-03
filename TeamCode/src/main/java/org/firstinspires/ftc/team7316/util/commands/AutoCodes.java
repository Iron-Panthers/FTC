package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.commands.ClimbForTime;
import org.firstinspires.ftc.team7316.commands.DriveDistance;
import org.firstinspires.ftc.team7316.commands.TurnGyro;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;

/**
 * Contains all the sequential commands to run
 */
public class AutoCodes {

    /*
    public static SequentialCommand exampleAutoSequence() {
        SomeCommand cmd1 = new SomeCommand();
        SomeCommand cmd2 = new SomeCommand();
        Command[] cmds = {cmd1, cmd2};
        return new SequentialCommand(cmds);
    }
    */

    public enum CheddarLocations {
        RIGHT,
        LEFT,
        CENTER
    }

    private static TurnGyro getCheddarTurn( CheddarLocations currentLocation) {
        if (currentLocation == CheddarLocations.RIGHT) {
            return new TurnGyro(Constants.cheddarRightTurn);
        } else if (currentLocation == CheddarLocations.LEFT) {
            return new TurnGyro(Constants.cheddarLeftTurn);
        } else {
            return new TurnGyro(0);
        }
    }

    public static SequentialCommand blueGoldSide() {
        Hardware.instance.gyroWrapper.resetHeading(Hardware.instance.gyroWrapper.angles().yaw); //reset gyro heading

        ClimbForTime dropDown = new ClimbForTime(1, 3, ClimbForTime.ClimbDirections.DOWN);
        //DRIVE AT CHEDDAR
        CheddarLocations currentLocation = CheddarLocations.RIGHT;

        TurnGyro cheddarTurn = getCheddarTurn(currentLocation);

        DriveDistance driveAtCheddar = new DriveDistance(Constants.inchesToTicks(Constants.driveCheddarDistance)); //THIS IS GUESS

        


        Command[] autoCommands = {dropDown, cheddarTurn, driveAtCheddar};
        return new SequentialCommand(autoCommands);
    }

}
