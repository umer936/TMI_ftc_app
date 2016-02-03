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
    DcMotor motorExtend;
    Servo Aiming;
    Servo Stick;
//    DcMotor motorCannon;

 //  final static double climberRight_MIN_RANGE = 0;
   // final static double climberRight_MAX_RANGE = 1;
    //final static double climberLeft_MIN_RANGE = 0;
    //final static double climberLeft_MAX_RANGE = 1;

   // Servo climberRight;
   // Servo climberLeft;
    public int direction = 1;
  //  public float cannonPower = 0;
    public float right;
    public float left;
    public float extend;
    public float aimAngle = (float)0.9;
    public float aimAngleChange = (float).005;
    public float stickAngle = (float)0.5;
    public float stickAngleChange = (float).005;
    //public float rescuelclimbers //controlls a the servo that the climbers are attached to.

    //double climberRightPosition;
    //double climberLeftPosition;
;
    public DriveTrain_Alex(){

    }

    @Override

    public void init() {
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_2");
        motorExtend = hardwareMap.dcMotor.get("motor_3");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
        Aiming = hardwareMap.servo.get("servo 1");
        Stick = hardwareMap.servo.get("servo 2");
    }
    @Override
    public void loop() {
        right = gamepad1.left_stick_y;
        left = gamepad1.right_stick_y;

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


        /*if (gamepad2.a) {
            if (direction == 0) {
                direction = 1;
            } else {
                direction = 0;
            }
        }*/

        if (gamepad1.a) {
            extend = 1;
        } else if (gamepad1.b) {
            extend = -1;
        } else {
            extend = 0;
        }

        if (gamepad1.y) {
            aimAngle += aimAngleChange;
            if (aimAngle>1){
                aimAngle=1;
            }
        } else if (gamepad1.x) {
            aimAngle -= aimAngleChange;
            if (aimAngle<0){
                aimAngle=0;
            }
        }

        if (gamepad1.right_bumper) {
            stickAngle += stickAngleChange;
            if (stickAngle>1){
                stickAngle=1;
            }
        } else if (gamepad1.left_bumper) {
            stickAngle -= stickAngleChange;
            if (stickAngle<0){
                stickAngle=0;
            }
        }

        Aiming.setPosition(aimAngle);
        motorRight.setPower(left);
        motorLeft.setPower(right);
        motorExtend.setPower(extend);
        Stick.setPosition(stickAngle);


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
        telemetry.addData("angle", "Direction: " + String.format("%.4f", aimAngle));
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

