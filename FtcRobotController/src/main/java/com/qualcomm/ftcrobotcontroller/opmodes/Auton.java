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

    // Servos
    Servo shelter;
    Servo hook;


    @Override
    public void runOpMode() throws InterruptedException {

        waitOneFullHardwareCycle();

        motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

//        shelter = hardwareMap.servo.get("servo_2");

        waitOneFullHardwareCycle();

        sleep(17000);

        waitOneFullHardwareCycle();

        // Go forward for 4 seconds.
        motorLeft.setPower(1);
        motorRight.setPower(1);
        sleep(3000);

        waitForNextHardwareCycle();

        motorLeft.setPower(0);
        motorRight.setPower(0);
        sleep(1000);

        waitForNextHardwareCycle();

        telemetry.addData("Speed", " Left=" + motorLeft.getPower() + " Right=" + motorRight.getPower());

        waitOneFullHardwareCycle();
    }
}
