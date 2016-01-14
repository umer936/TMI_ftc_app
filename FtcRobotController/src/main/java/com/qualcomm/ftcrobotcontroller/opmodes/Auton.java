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

        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        shelter = hardwareMap.servo.get("servo_2");

        waitOneFullHardwareCycle();

        sleep(13000);

        // Go forward for 4 seconds.
        motorLeft.setPower(1);
        motorRight.setPower(1);
        sleep(4000);
//
//        //turn left for 1 second.
//        motorLeft.setPower(-1);
//        motorRight.setPower(1);
//        sleep(1000);
        
        telemetry.addData("Speed", " Left=" + motorLeft.getPower() + " Right=" + motorRight.getPower());

        waitOneFullHardwareCycle();
    }
}
