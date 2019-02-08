package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.commands.DriveDistance;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;
    @Autonomous(name = "Auto Drive Distance")
public class AutoDriveDistance extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new DriveDistance(Constants.inchesToTicks(5)));
    }

    @Override
    public void onLoop() {

    }
}
