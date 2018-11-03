package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;

import org.firstinspires.ftc.team7316.commands.DriveDistance;
import org.firstinspires.ftc.team7316.commands.TurnGyro;
import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.GyroAngles;
import org.firstinspires.ftc.team7316.util.GyroWrapper;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="BlueAlliance GoldSide")
public class BlueGoldAuto extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(AutoCodes.blueGoldSide());
        Hardware.instance.gyroWrapper.resetHeading(Hardware.instance.gyroWrapper.angles().yaw);
    }

    @Override
    public void onLoop() {
    }
}
