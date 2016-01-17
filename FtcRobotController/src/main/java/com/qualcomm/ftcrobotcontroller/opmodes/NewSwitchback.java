package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by umer936 on 1/17/16.
 */
public class NewSwitchback extends OpMode {

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

    public int hookToggle = 0; 
    public int shelterToggle = 0;

    float driverRight;
    float driverLeft;

    float operatorRight;

    @Override
    public void init(){
        
        // Motor Map 
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
        hookLeft.setPosition(1.0); 
        leftSki.setPosition(0.5);
        rightSki.setPosition(0.5);

    }

    @Override 
    public void loop(){

        /* Driving wheeeee */ 
        
        driverRight = Range.clip(gamepad1.right_stick_y, -1, 1);
        driverLeft = Range.clip(gamepad1.left_stick_y, -1, 1);
        operatorRight = Range.clip(gamepad2.right_stick_y, -1, 1);

        driverRight = (float)scaleInput(driverRight);
        driverLeft = (float)scaleInput(driverLeft);
        operatorRight = (float)scaleInput(operatorRight);

        driveRight.setPower(driverRight); 
        driveLeft.setPower(driverLeft); 
        oneoneeightR.setPower(operatorRight/2);
        oneoneeightL.setPower(operatorRight/2);

        /* Servos */

        // Hooks
        if(gamepad2.x){
            hookRight.setPosition(0.5);
            hookLeft.setPosition(0.5);
        }
        if(gamepad2.y){            
            hookRight.setPosition(0.0);
            hookLeft.setPosition(1.0);
        }
        // Skis 
        if(gamepad2.dpad_down){
            leftSki.setPosition(0.5);
            rightSki.setPosition(0.5);
        }
        if(gamepad2.dpad_up){
            leftSki.setPosition(0.5);
            rightSki.setPosition(0.5);
        }


        /* Telemetry */ 
        telemetry.addData("Text", "*** Robot Data***");
        telemetry.addData("left", "left pwr: " + String.format("%.2f", driverLeft));
        telemetry.addData("right", "right pwr: " + String.format("%.2f", driverRight));

    }


    double scaleInput(double dVal)  {
        double[] scaleArray = { 0.0, 0.05, 0.09, 0.10, 0.12, 0.15, 0.18, 0.24,
                0.30, 0.36, 0.43, 0.50, 0.60, 0.72, 0.85, 1.00, 1.00 };

        // get the corresponding index for the scaleInput array.
        int index = (int) (dVal * 16.0);

        // index should be positive.
        if (index < 0) {
            index = -index;
        }

        // index cannot exceed size of array minus 1.
        if (index > 16) {
            index = 16;
        }

        // get value from the array.
        double dScale = 0.0;
        if (dVal < 0) {
            dScale = -scaleArray[index];
        } else {
            dScale = scaleArray[index];
        }

        // return scaled value.
        return dScale;
    }
}
