package org.firstinspires.ftc.team7316.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.team7316.commands.TurnGyroSimple;
import org.firstinspires.ftc.team7316.util.Scheduler;
import org.firstinspires.ftc.team7316.util.modes.AutoBaseOpMode;

@Autonomous(name="Test Turn Right")
public class TestTurnRight extends AutoBaseOpMode {
    @Override
    public void onInit() {
        Scheduler.instance.add(new TurnGyroSimple(45));
    }

    @Override
    public void onLoop() {

    }
}
