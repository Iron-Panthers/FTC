package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.commands.AutoCodes;
@Autonomous(name="Auto Crater")

public class AutoShortCrater extends OpMode {
    @Override
    public void init() {
        Scheduler.instance.add(AutoCodes.turnAndHitCheddar());

    }

    @Override
    public void loop() {

    }
}
