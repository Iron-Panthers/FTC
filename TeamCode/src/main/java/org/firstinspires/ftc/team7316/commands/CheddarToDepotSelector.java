package org.firstinspires.ftc.team7316.commands;

import org.firstinspires.ftc.team7316.util.Alliance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;
import org.firstinspires.ftc.team7316.util.Vec2;

//Created by aaron on 11/3/18

public class CheddarToDepotSelector extends Command {
    //INIT variables
    private CheddarLocations currentCheddarLocation; //current cheddar position
    private int commandIndex = 0; //command index tracker
    private boolean foundCheddar = false;
    private Command currentCommand;

    //gets turn to make to face cheddar
    private TurnGyro getFaceCheddarTurn(CheddarLocations currentLocation) {
        if (currentLocation == CheddarLocations.RIGHT) {
            return new TurnGyro(Constants.cheddarRightTurn);
        } else if (currentLocation == CheddarLocations.LEFT) {
            return new TurnGyro(Constants.cheddarLeftTurn);
        } else {
            return new TurnGyro(0);
        }
    }

    public enum CheddarLocations { //possible cheddar locations
        RIGHT,
        LEFT,
        CENTER
    }

    public CheddarToDepotSelector(Alliance allianceColor) {
        requires(Subsystems.instance.driveSubsystem);
    }

    @Override
    public void init() {
        commandIndex = 0;
        //currentCommand = new GetCheddarLocation();
        //currentCommand.init();
    }

    @Override
    public void loop() {
        if (commandIndex != 0) { //command 0 is not a command because this is jank
            currentCommand.loop();
        }

        if (commandIndex == 0) {
            //find the cheddar
            //when you find it:
            if (foundCheddar) {
                commandIndex++;
                currentCommand = getFaceCheddarTurn(currentCheddarLocation); //turn to cheddar
                currentCommand.init();
            } else {
                //look for the cheddar here


                long convexHullMaxY = 10; //random shit to test logic
                long convexHullMinY = 0;
                long convexHullMaxX = 10;
                long convexHullMinX = 0;

                long avgX = (convexHullMaxX+convexHullMinX)/2;
                long avgY = (convexHullMaxY+convexHullMinY)/2;
                //split screen into thirds
                long screenWidth = 176*4;
                long screenHeight = 144*4;

                if (avgX < (screenWidth/3)) {
                    currentCheddarLocation = CheddarLocations.LEFT;
                } else if (avgX > ((screenWidth/3)*2)) {
                    currentCheddarLocation = CheddarLocations.RIGHT;
                } else {
                    currentCheddarLocation = CheddarLocations.CENTER;
                }

                foundCheddar = true;
            }
        } else if (commandIndex == 1) {
            if (currentCommand.shouldRemove()) {
                currentCommand.interrupt();
                currentCommand = new DriveDistance(Constants.inchesToTicks(Constants.driveCheddarDistance)); //THIS IS GUESS
                commandIndex++;
            }
        } else if (commandIndex == 2) {
            currentCommand.interrupt();
            commandIndex++;
        }
    }

    @Override
    public boolean shouldRemove() {
        return commandIndex >= 3; //should remove? are all the commands done
    }

    @Override
    protected void end() {
        Subsystems.instance.driveSubsystem.resetMotors();
    }
}
