package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by luke on 1/17/2016.
 */
public class Animatronio2 extends LinearOpMode{
    // Motors
    DcMotor driveRight;
    DcMotor driveLeft;
    DcMotor oneoneeightR;
    DcMotor oneoneeightL;

    // Servos
    Servo hookRight;
    Servo hookLeft;
    Servo leftSki;
    Servo rightSki;


    @Override
    public void runOpMode() throws InterruptedException {

        waitOneFullHardwareCycle();

        driveLeft = hardwareMap.dcMotor.get("left");
        driveRight = hardwareMap.dcMotor.get("right");
        oneoneeightL = hardwareMap.dcMotor.get("L118");
        oneoneeightR = hardwareMap.dcMotor.get("R118");
        driveLeft.setDirection(DcMotor.Direction.REVERSE);
        oneoneeightL.setDirection(DcMotor.Direction.REVERSE);

        // Servo Map
        hookRight = hardwareMap.servo.get("hookright");
        hookLeft = hardwareMap.servo.get("hookleft");
        leftSki = hardwareMap.servo.get("leftski");
        rightSki = hardwareMap.servo.get("rightski");

        // Initialize Servos
        hookRight.setPosition(0.0);
        hookLeft.setPosition(0.85);
        leftSki.setPosition(0.5);
        rightSki.setPosition(0.5);

        waitOneFullHardwareCycle();

        sleep(13000);

        // Go forward for 3 seconds.
        driveLeft.setPower(1);
        driveRight.setPower(1);
        sleep(3000);

        //turn right for 1 second.
        driveLeft.setPower(1);
        driveRight.setPower(-1);
        sleep(1000);

        //turn left for half a second
        driveLeft.setPower(1);
        driveRight.setPower(-1);
        sleep(500);


        // Go forward for 2.5 seconds.
        driveLeft.setPower(1);
        driveRight.setPower(1);
        sleep(2500);

        //hook into the bar and turn off the motors
        hookRight.setPosition(0.6);
        hookLeft.setPosition(0.25);
        sleep(500);
        driveLeft.setPower(0);
        driveRight.setPower(0);


        telemetry.addData("Speed", " Left=" + driveLeft.getPower() + " Right=" + driveRight.getPower());

        waitOneFullHardwareCycle();
    }
}
