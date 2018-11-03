package org.firstinspires.ftc.team7316.commands;

import android.util.Log;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Const;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.GyroAngles;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.PID;
import org.firstinspires.ftc.team7316.util.commands.Command;
import org.firstinspires.ftc.team7316.util.copypastaLib.CombinedPath;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

import java.util.ArrayList;
import java.util.Timer;

public class TurnGyro extends Command {

    private int deltaHeading;
    private CombinedPath.LongitudalTrapezoid anglePath;
    ElapsedTime timer = new ElapsedTime();
    double lastSeconds;
    double lastHeading = 0;

    private PID turnPID = new PID(Constants.TURN_P, Constants.TURN_I, Constants.TURN_D, Constants.TURN_F, 0, true);

    private GyroAngles angles;
    private static final double DEGREES_THRESH = 3;

    private int timesCompleted = 0;
    private final int TARGET_REACHED_THRESHOLD = 10;

    public TurnGyro(int deltaHeading) {
        // prevents the robot from turning in a stupid way
        int modA = (deltaHeading - 180) % 360;
        if (modA < 0) {
            modA += 360;
        }
        modA -= 180;
        this.deltaHeading = modA;

        if (this.deltaHeading >= 0) {
            anglePath = new CombinedPath.LongitudalTrapezoid(0, this.deltaHeading, Constants.MAX_DEGREES_SPEED, Constants.MAX_DEGREES_ACCEL);
        } else {
            anglePath = new CombinedPath.LongitudalTrapezoid(0, this.deltaHeading, -Constants.MAX_DEGREES_SPEED, -Constants.MAX_DEGREES_ACCEL);
        }

    }

    @Override
    public void init() {
        timer.reset();
        turnPID.reset();
        turnPID.setPath(anglePath);
        lastSeconds = timer.seconds();

        GyroAngles inititalAngles = Hardware.instance.gyroWrapper.angles();
        Hardware.instance.gyroWrapper.resetHeading(inititalAngles.yaw);
        inititalAngles.heading = 0;
        this.angles = inititalAngles;

        Hardware.log("delta heading", this.deltaHeading);
    }

    @Override
    public void loop() {
        double dtime = timer.seconds() - lastSeconds;
        lastSeconds = timer.seconds();

        GyroAngles deltaAngles = Hardware.instance.gyroWrapper.angles();
        this.angles.add(deltaAngles);
        Hardware.instance.gyroWrapper.resetHeading(deltaAngles.yaw);
        Hardware.log("f", anglePath.getSpeed(timer.seconds()));
        Hardware.log("time", timer.seconds());

        double power = turnPID.getPower(anglePath.getPosition(timer.seconds()) - angles.heading, dtime, angles.heading, angles.heading - lastHeading);
        lastHeading = angles.heading;

        Hardware.instance.leftmotorWrapper.setPower(power);
        Hardware.instance.rightmotorWrapper.setPower(-power);
        Hardware.instance.centermotorWrapper.setPower(-power);

        if((Math.abs(deltaHeading - angles.heading) < DEGREES_THRESH)) {
            timesCompleted++;
        }

    }

    @Override
    public boolean shouldRemove() {
//        return turnPID.finished() || (Math.abs(deltaHeading - angles.heading) < DEGREES_THRESH);
//        return timesCompleted >= TARGET_REACHED_THRESHOLD;
        return timer.seconds() > anglePath.getTotalTime() + 1;// || (Math.abs(deltaHeading - angles.heading) < DEGREES_THRESH);
    }

    @Override
    protected void end() {

        writeData("left", turnPID.dataPoints);

        Hardware.instance.leftmotorWrapper.setPower(0);
        Hardware.instance.rightmotorWrapper.setPower(0);
        Hardware.instance.centermotorWrapper.setPower(0);
    }

    private void writeData(String motorName, ArrayList<String[]> dataList) {
        Log.d(motorName+"_AutoInfo","Info:\n" +
                "\tPIDF Constants: " + String.format("%s,%s,%s,%s\n", Constants.DRIVE_P, Constants.DRIVE_I, Constants.DRIVE_D, Constants.DRIVE_F) +
                "\tData Order: Time, Error, Predicted Speed, Actual Speed, Predicted Position, Actual Position Power \n");

        for (String[] data : dataList) {
            String line = "";
            for (String str : data) {
                line += str + ",";
            }
            Log.d(motorName+"_AutoData", line);
        }
    }
}
