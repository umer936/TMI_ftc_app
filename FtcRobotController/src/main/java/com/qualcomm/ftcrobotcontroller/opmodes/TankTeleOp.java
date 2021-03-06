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

    DcMotor winchRight;
    DcMotor winchLeft;

    DcMotor Lift;
    DcMotor Intake;

    // Initialize Servos
    Servo hookRight;
    Servo hookLeft;
    Servo shelter;
    Servo arm;
    Servo triggers;
    Servo gate;

    double hookRightPosition = 1;
    double hookLeftPosition = 0;
    double shelterPosition = 0;

    public int direction = 1;
    public double liftmultiplier = 0.25;

    public TankTeleOp() {

    }

    @Override
    public void init() {

	    motorRight = hardwareMap.dcMotor.get("motorRight");
        motorLeft = hardwareMap.dcMotor.get("motorLeft");
//        motorLeft.setDirection(DcMotor.Direction.REVERSE);

        winchRight = hardwareMap.dcMotor.get("winchRight");
        winchLeft = hardwareMap.dcMotor.get("winchLeft");
        winchRight.setDirection(DcMotor.Direction.REVERSE);

        Lift = hardwareMap.dcMotor.get("Lift");
        Intake = hardwareMap.dcMotor.get("Intake");

        hookRight = hardwareMap.servo.get("hookRight");
        hookLeft = hardwareMap.servo.get("hookLeft");
        shelter = hardwareMap.servo.get("shelter");
        arm = hardwareMap.servo.get("arm");
        gate = hardwareMap.servo.get("gate");
        triggers = hardwareMap.servo.get("triggers");

        triggers.setPosition(0);
//        triggerLeft.setPosition(0);
//        triggerRight.setPosition(0.5);
        arm.setPosition(1);

    }


    @Override
    public void loop() {

        float right = gamepad1.left_stick_y;
        float left = gamepad1.right_stick_y;
        float opleft = gamepad2.left_stick_y;
        float opright = gamepad2.right_stick_y;

        // clip the right/left values so that the values never exceed +/- 1
        right = Range.clip(right, -1, 1);
        left = Range.clip(left, -1, 1);
        opleft = Range.clip(opleft, -1, 1);
        opright = Range.clip(opright, -1, 1);


        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        right = (float)scaleInput(right);
        left = (float)scaleInput(left);
        opleft = (float)scaleInput(opleft);
        opright = (float)scaleInput(opright);

        // write the values to the motors
        motorRight.setPower(right);
        motorLeft.setPower(-left);

        winchLeft.setPower(opleft);
        winchRight.setPower(opleft);

        Lift.setPower(opright*liftmultiplier);

        if(gamepad1.left_bumper) {
            Intake.setPower(1);
        } else if(gamepad1.right_bumper) {
            Intake.setPower(-1);
        }
        else {
            Intake.setPower(0);
        }

        if(gamepad2.a)  //up
        {
            hookRightPosition = 0;
            hookLeftPosition = 1;
        }
        if(gamepad2.b) //down
        {
            hookRightPosition = 1;
            hookLeftPosition = 0;
        }
        if(gamepad2.left_bumper) {
            shelterPosition = 1;
        }
        if(gamepad2.right_bumper) {
            shelterPosition = 0;
        }

        if(gamepad2.y) {
            arm.setPosition(0);
        }
        if(gamepad2.x) {
            arm.setPosition(1);
        }

        if(gamepad2.dpad_left) {
            triggers.setPosition(.5);
        }
        if(gamepad2.dpad_right) {
            triggers.setPosition(0);
        }

//        if(gamepad2.dpad_left) {
//            triggerLeft.setPosition(0.6);
//        }
//        if(gamepad2.dpad_up) {
//            triggerLeft.setPosition(0);
//        }
//        if(gamepad2.dpad_right) {
//            triggerRight.setPosition(0);
//        }
//        if(gamepad2.dpad_down) {
//            triggerRight.setPosition(0.5);
//        }

        if(gamepad2.back) {
            gate.setPosition(.6);
        }
        if(gamepad2.start) {
            gate.setPosition(.4);
        }

        hookRightPosition = Range.clip(hookRightPosition, 0, 1);
        hookLeftPosition = Range.clip(hookLeftPosition, 0, 1);
        shelterPosition = Range.clip(shelterPosition, 0, 1);

        hookRight.setPosition(hookRightPosition);
        hookLeft.setPosition(hookLeftPosition);
        shelter.setPosition(shelterPosition);

        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left", "left pwr: " + String.format("%.2f", left));
        telemetry.addData("right", "right pwr: " + String.format("%.2f", right));
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
    public void stop(){
    }
}
