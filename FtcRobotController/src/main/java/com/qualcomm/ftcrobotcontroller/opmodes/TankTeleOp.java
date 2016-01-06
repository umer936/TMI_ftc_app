package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.ServoConfiguration;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by umer936 on 10/26/15.
 */
public class TankTeleOp extends OpMode {

    // Initialize Motors
    DcMotor motorRight;
    DcMotor motorLeft;
//    DcMotor motorIntake;

    //Initialize Servos
    Servo hook;
    Servo climberLeft;
    Servo climberRight;
    Servo shelter;

    public Boolean aToggle = false; //part of the toggle for the a button
    float intake = 0;

    public TankTeleOp() {

    }

    @Override
    public void init() {
	    motorRight = hardwareMap.dcMotor.get("motor_2");
        motorLeft = hardwareMap.dcMotor.get("motor_1");
        motorLeft.setDirection(DcMotor.Direction.REVERSE);
//        motorIntake = hardwareMap.dcMotor.get("motor_3");
        climberLeft = hardwareMap.servo.get("servo_1");
        climberRight = hardwareMap.servo.get("servo_2");
        shelter = hardwareMap.servo.get("servo_3");
        hook = hardwareMap.servo.get("servo_4");
    }


    @Override
    public void loop() {
        //trying to get an intake to run. Idk what I'm doing someone will have to check later.
        float right = gamepad1.left_stick_y;
        float left = gamepad1.right_stick_y;
        //float intake;

        /*if(gamepad1.a == true)
        {
            intake =1;
        }
        else
        {
            intake =0;
        }*/  //Changing button a to toggle  -- TW
/*
        if ((!aToggle.equals(gamepad1.a))){
            if (gamepad1.a) {
                if (intake == 0) {
                    intake = 1;
                } else {
                    intake = 0;
                }
            }
            aToggle=gamepad1.a;
        }


        motorIntake.setPower(intake);
*/

        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);


        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        right = (float)scaleInput(right);
        left = (float)scaleInput(left);


        // write the values to the motors
        motorRight.setPower(right);
        motorLeft.setPower(left);

        if(gamepad2.a)
        {
            hook.setPosition(.5);
        }
        if(gamepad2.b)
        {
            hook.setPosition(0);
        }
        if(gamepad2.dpad_left)
        {
            climberLeft.setPosition(.7);
        }
        if(gamepad2.dpad_right)
        {
            climberRight.setPosition(.7);
        }
        if(gamepad2.x)
        {
            shelter.setPosition(0);
        }
        if(gamepad2.y)
        {
            shelter.setPosition(.5);
        }
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("", "left  pwr: " + String.format("%.2f", left));
        telemetry.addData("", "right pwr: " + String.format("%.2f", right));


    }

    /*
     * Code to run when the op mode is first disabled goes here
     *
     * @see com.qualcomm.robotcore.eventloop.opmode.OpMode#stop()
     */

    /*
     * This method scales the joystick input so for low joystick values, the
     * scaled value is less than linear.  This is to make it easier to drive
     * the robot more precisely at slower speeds.
     */
    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
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