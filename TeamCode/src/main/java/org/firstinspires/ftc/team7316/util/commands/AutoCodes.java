package org.firstinspires.ftc.team7316.util.commands;

import org.firstinspires.ftc.team7316.commands.CheddarToDepotSelector;
import org.firstinspires.ftc.team7316.commands.ClimbForTime;
import org.firstinspires.ftc.team7316.commands.DriveDistance;
import org.firstinspires.ftc.team7316.commands.MoveMarkerServo;
import org.firstinspires.ftc.team7316.commands.TurnGyro;
import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.commands.flow.SequentialCommand;
import org.firstinspires.ftc.team7316.util.subsystems.MarkerSubsystem;

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
    //Written by aaron on 11/3/18

    public static SequentialCommand blueGoldSide() {
        Hardware.instance.gyroWrapper.resetHeading(Hardware.instance.gyroWrapper.angles().yaw); //reset gyro heading

        //PART 1: Lower from lander
        ClimbForTime dropDown = new ClimbForTime(1, 3, ClimbForTime.ClimbDirections.DOWN);

        //PART 2: Find cheddar and drive towards it, go to pit (packaged into own command because it's kinda complex)
        CheddarToDepotSelector cheddarToDepot = new CheddarToDepotSelector(Alliance.BLUE);

        //PART 3: Drop the team marker
        MoveMarkerServo lowerServo = new MoveMarkerServo(MarkerSubsystem.MarkerPositions.DOWN); //builtin wait
        MoveMarkerServo raiseServo = new MoveMarkerServo(MarkerSubsystem.MarkerPositions.UP);
        MoveMarkerServo lowerServo2 = new MoveMarkerServo(MarkerSubsystem.MarkerPositions.DOWN); //builtin wait
        MoveMarkerServo raiseServo2 = new MoveMarkerServo(MarkerSubsystem.MarkerPositions.UP);

        //PART 4: Turn and drive backwards to crater so that we can get the extra points
        TurnGyro turnAround = new TurnGyro(180); //flip around so front w/team marker is facing crater
        DriveDistance driveToCrater = new DriveDistance(-Constants.inchesToTicks(Constants.depotToCraterDistance)); //guess

        //PART 5: Lower team marker so that we get the "parking points"
        MoveMarkerServo getBonusPoints = new MoveMarkerServo(MarkerSubsystem.MarkerPositions.HALFDOWN);

        //aaaand we're done here boys






        Command[] autoCommands = {dropDown, cheddarToDepot, lowerServo, raiseServo, lowerServo2, raiseServo2, turnAround, driveToCrater, getBonusPoints};
        return new SequentialCommand(autoCommands);
    }

}
