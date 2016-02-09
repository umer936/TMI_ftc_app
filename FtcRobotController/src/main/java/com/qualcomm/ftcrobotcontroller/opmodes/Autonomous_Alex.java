package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


public class Autonomous_Alex extends LinearOpMode {

    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motorExtend;
    Servo Aiming;
    Servo Stick;

    //  final static double climberRight_MIN_RANGE = 0;
    // final static double climberRight_MAX_RANGE = 1;
    //final static double climberLeft_MIN_RANGE = 0;
    //final static double climberLeft_MAX_RANGE = 1;

    // Servo climberRight;
    // Servo climberLeft;
    public int direction = 1;
    public float right;
    public float left;
    //public float extend;
    public float aimAngle = (float)1.0;
    //public float aimAngleChange = (float).005;
    public float stickAngle = (float)0.0;
    //public float stickAngleChange = (float).007;

    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_2");
        motorExtend = hardwareMap.dcMotor.get("motor_3");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        Aiming = hardwareMap.servo.get("servo 1");
        Stick = hardwareMap.servo.get("servo 2");
        Aiming.setPosition(aimAngle);
        Stick.setPosition(stickAngle);

        motorRight.setPower(1);
        motorLeft.setPower(1);
        sleep(2000);
        //motorRight.setPower(1);
        //motorLeft.setPower(0.3);
        sleep(1500);
        //motorRight.setPower(1);
        //motorLeft.setPower(1);

        //DO NOT ALTER ANYTHING BELOW THIS COMMENT!
        motorRight.setPower(0);
        motorLeft.setPower(0);
    }
}
