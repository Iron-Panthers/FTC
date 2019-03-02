package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.util.Constants;
import org.firstinspires.ftc.team7316.util.Hardware;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;
import org.firstinspires.ftc.team7316.util.subsystems.Subsystems;

@Autonomous(name="NO PID Test")

public class NoPIDTest extends AutoBaseOpMode {


    @Override
    public void onInit() {
    }

    @Override
    public void onLoop() {
        Hardware.instance.leftmotor.setPower((-.7/Constants.DRIVE_M_LEFT)+(-1 * (Constants.DRIVE_B_LEFT/ Constants.DRIVE_M_LEFT )));
        Hardware.instance.leftmotor.setPower((-.7/Constants.DRIVE_M_RIGHT)+(-1 * (Constants.DRIVE_B_RIGHT/ Constants.DRIVE_M_RIGHT)));

    }

}