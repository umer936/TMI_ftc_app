package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by umer936 on 1/8/16.
 */
public class Auton extends LinearOpMode {

    // Motors
    DcMotor motorRight;
    DcMotor motorLeft;

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
        waitOneFullHardwareCycle();

        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        waitOneFullHardwareCycle();
        sleep(12000);

        motorLeft.setPower(-.5);
        motorRight.setPower(.5);
        sleep(1000);
        waitOneFullHardwareCycle();

        motorLeft.setPower(0);
        motorRight.setPower(0);
        waitOneFullHardwareCycle();
    }
}