package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

public class Autonomous_Alex extends LinearOpMode {

    DcMotor motorRight;
    DcMotor motorLeft;

    //  final static double climberRight_MIN_RANGE = 0;
    // final static double climberRight_MAX_RANGE = 1;
    //final static double climberLeft_MIN_RANGE = 0;
    //final static double climberLeft_MAX_RANGE = 1;

    // Servo climberRight;
    // Servo climberLeft;
    public int direction = 1;

    @Override
    public void runOpMode() throws InterruptedException {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        motorRight.setPower(1);
        motorLeft.setPower(1);
        sleep(9000);

        motorRight.setPower(1);
        motorLeft.setPower(-1);
        sleep(2000);

        motorRight.setPower(1);
        motorLeft.setPower(1);
        sleep(8000);

        motorRight.setPower(0);
        motorLeft.setPower(0);



    }

}
