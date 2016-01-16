package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by ROBOTICS on 1/8/2016.
 */
public class DriveTrain_Alex extends OpMode {
    DcMotor motorRight;
    DcMotor motorLeft;

 //  final static double climberRight_MIN_RANGE = 0;
   // final static double climberRight_MAX_RANGE = 1;
    //final static double climberLeft_MIN_RANGE = 0;
    //final static double climberLeft_MAX_RANGE = 1;

   // Servo climberRight;
   // Servo climberLeft;
    public int direction = 1;

    //double climberRightPosition;
    //double climberLeftPosition;

    public DriveTrain_Alex (){

    }

    @Override
    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        float right = gamepad1.left_stick_y;
        float left = gamepad1.right_stick_y;

        if (gamepad1.start) {
            if (direction == 0) {
                direction = 1;
            } else {
                direction = 0;
            }
        }


        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);

        right = (float) scaleInput(right);
        left = (float) scaleInput(left);

        if (direction == 1) {
            right = -right;
            left = -left;
        }

        motorRight.setPower(left);
        motorLeft.setPower(right);

        /*if (gamepad2.b)
        {
            climberRightPosition = -.5;
        }
        if (gamepad2.a)
        {
            climberRightPosition = .5;
        }

        if (gamepad2.y)
        {
            climberLeftPosition = -.5;
        }
        if (gamepad2.x)
        {
            climberLeftPosition = .5;
        }

        climberRightPosition = Range.clip(climberRightPosition, climberRight_MIN_RANGE, climberRight_MAX_RANGE);
        climberLeftPosition = Range.clip(climberLeftPosition, climberLeft_MIN_RANGE, climberLeft_MAX_RANGE);

        climberRight.setPosition(climberRightPosition);
        climberRight.setPosition(climberLeftPosition);
*/
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left", "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("right", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("Direction", "Direction: " + String.format("%d", direction));
    }

    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        int index = (int) (dVal * 16.0);
        if (index < 0) {
            index = -index;
        }
        if (index > 16) {
            index = 16;
        }

        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        return dScale;
    }
    @Override
    public void stop(){}

}

