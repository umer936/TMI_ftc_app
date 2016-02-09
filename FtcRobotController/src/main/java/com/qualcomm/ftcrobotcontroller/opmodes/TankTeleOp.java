package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;


/**
 * Created by umer936 on 10/26/15.
 */
public class TankTeleOp extends OpMode {

    // Initialize Motors
    DcMotor motorRight;
    DcMotor motorLeft;
//    DcMotor motorIntake;
    DcMotor winchRight;
    DcMotor winchLeft;


    // Initialize Servos
    Servo hookRight;
    Servo hookLeft;
    //Servo climberLeft;
    //Servo climberRight;
    Servo shelter;

    //public Boolean aToggle = false; //part of the toggle for the a button
    //float intake = 0;
    public int direction = 1;

    double hookRightPosition;
    double hookLeftPosition;
    double shelterPosition = 0;
    //double shelterPosition;
    //double climberRightPosition;
    //double climberLeftPosition;

    public TankTeleOp() {

    }

    @Override
    public void init() {
	    motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
        motorRight.setDirection(DcMotor.Direction.REVERSE);
//        motorIntake = hardwareMap.dcMotor.get("motor_3");
        winchRight = hardwareMap.dcMotor.get("winchRight");
        winchLeft = hardwareMap.dcMotor.get("winchLeft");
        winchRight.setDirection(DcMotor.Direction.REVERSE);
        
       // climberLeft = hardwareMap.servo.get("servo_1");
        //climberRight = hardwareMap.servo.get("servo_2");
        //shelter = hardwareMap.servo.get("servo_4");
        hookRight = hardwareMap.servo.get("hookRight");
        hookLeft = hardwareMap.servo.get("hookLeft");
        shelter = hardwareMap.servo.get("shelter");

        //shelter.setPosition(0);
    }


    @Override
    public void loop() {
        //trying to get an intake to run. Idk what I'm doing someone will have to check later.
        float right = gamepad1.left_stick_y;
        float left = gamepad1.right_stick_y;
        float opleft = gamepad2.left_stick_y;
        //float intake;

/*
        // to flip directions.
        if(gamepad1.start)
        {
            if(direction == 0)
            {
                direction = 1;
            }
            else
            {
                direction = 0;
            }

        }*/

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
        opleft = Range.clip(opleft, -1, 1);


        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        right = (float)scaleInput(right);
        left = (float)scaleInput(left);
        opleft = (float)scaleInput(opleft);

        if(direction == 1)
        {
//            left = -left;
//            right = -right;
        }

        // write the values to the motors
        motorRight.setPower(-left);
        motorLeft.setPower(-right);

        winchLeft.setPower(opleft);
        winchRight.setPower(opleft);

        if(gamepad2.a)
        {
            hookRightPosition = 0;
            hookLeftPosition = 1;
        }
        if(gamepad2.b)
        {
            hookRightPosition = 1;
            hookLeftPosition = 0;
        }
        if(gamepad2.left_bumper)
        {
            shelterPosition = 1;
        }
        if(gamepad2.right_bumper)
        {
            shelterPosition = 0;
        }

        /*
        if(gamepad2.left_bumper)
        {
            climberRightPosition = .75;
        }
        if(gamepad2.right_bumper)
        {
            climberRightPosition = 0;
        }
        */


        hookRightPosition = Range.clip(hookRightPosition, 0, 1);
        hookLeftPosition = Range.clip(hookLeftPosition, 0, 1);
        shelterPosition = Range.clip(shelterPosition, 0, 1);
        //climberRightPosition = Range.clip(climberRightPosition, 0, 1);
        //climberLeftPosition = Range.clip(climberLeftPosition, 0, 1);

        hookRight.setPosition(hookRightPosition);
        hookLeft.setPosition(hookLeftPosition);
        shelter.setPosition(shelterPosition);
        //climberRight.setPosition(climberRightPosition);
        //climberLeft.setPosition(climberLeftPosition);


        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left", "left pwr: " + String.format("%.2f", left));
        telemetry.addData("right", "right pwr: " + String.format("%.2f", right));
        telemetry.addData("Direction", "Direction: " + String.format("%d", direction));
        telemetry.addData("servos:", "hookRight:" + hookRightPosition + "hookLeft:"+ hookLeftPosition+" shelter:" + shelterPosition);
        telemetry.addData("Lift", "Sending=" + String.format("%.2f",opleft) + " Left=" + winchLeft.getPower() + " Right=" + winchRight.getPower());

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
