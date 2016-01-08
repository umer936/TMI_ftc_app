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
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        shelter = hardwareMap.servo.get("servo_");
    }

    @Override
    public void start() {

    }
}
