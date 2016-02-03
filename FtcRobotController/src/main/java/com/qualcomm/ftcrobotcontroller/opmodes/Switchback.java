package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by luke on 1/13/2016.
 */
public class Switchback extends OpMode {

    // Initialize motors and servos
    DcMotor motorRight;
    DcMotor motorLeft;
    DcMotor motor118R;
    DcMotor motor118L;

    DcMotor motorLeftWinch;
    DcMotor motorRightWinch;
    DcMotor motorIntake;
    Servo hookRight;
    Servo hookLeft;
    //Servo bucket;
    Servo leftSki;
    Servo rightSki;
    //Servo intakeHeight;
    //Servo shelterPeople;



    // Toggle variables
    public boolean bucketToggle = false;
    public boolean shelterPeopleToggle = false;
    public boolean hookToggle = false;
    public boolean intakeHeightToggle = false;
    public boolean skiToggle = false;

    //Initialization positions for Servos
    double hookLeftInit = 1.0;
    double hookRightInit = 0.0;
    double shelterPeopleInit = 0.5;
    double bucketInit = 1.0;
    double intakeHeightInit = 0.5;
    double leftSkiInit = 0.5;
    double rightSkiInit = 0.5;

    //Multipliers for speed modifications
    float multiplierDrive = 1;
    float multiplier118 = 1;
    float multiplierLift = 1;
    float multiplierJog = 1;

    //Speed for fixed-speed intake
    float intakeSpeed = 1;

    //Original and Deployed Position Variables
    double hookRightOriginal = 1.0;
    double hookRightDeployed = 1.0;

    double hookLeftOriginal = 0.0;
    double hookLeftDeployed = 0.0;

    double bucketOriginal = 1.0;
    double bucketDeployed = 0.5;

    double intakeHeightOriginal = 1;
    double intakeHeightDeployed = 0;

    double shelterPeopleOriginal = 1;
    double shelterPeopleDeployed = 0;

    double leftSkiOriginal = 0.5;
    double leftSkiDeployed = 0.5;

    double rightSkiOriginal = 0.5;
    double rightSkiDeployed = 0.5;

    float driverRight;
    float driverLeft;
    float operatorRight;
    float operatorLeft;
    boolean operatorX;
    float operatorTriggerLeft;
    float operatorTriggerRight;
    boolean operatorBumperRight;
    boolean operatorBumperLeft;
    boolean operatorDpadUP;
    boolean operatorDpadDOWN;



    @Override
    public void init(){
        // Define Motor mapping to variables
        motorRight = hardwareMap.dcMotor.get("right");
        motorLeft = hardwareMap.dcMotor.get("left");
        motor118L = hardwareMap.dcMotor.get("118L");
        motor118R = hardwareMap.dcMotor.get("118R");
        motor118R.setDirection(DcMotor.Direction.REVERSE);
        //motorIntake = hardwareMap.dcMotor.get("motor_8");
        //motorLeftWinch = hardwareMap.dcMotor.get("motor_7");
        //motorRightWinch = hardwareMap.dcMotor.get("motor 3");

        //Define Servo mapping to variables
        hookLeft = hardwareMap.servo.get("hookleft");
        hookRight = hardwareMap.servo.get("hookright");
        leftSki = hardwareMap.servo.get("leftski");
        rightSki = hardwareMap.servo.get("rightski");

//        bucket = hardwareMap.servo.get("servo_3");
//        intakeHeight = hardwareMap.servo.get("servo_4");
//        shelterPeople = hardwareMap.servo.get("servo_5");

        //Initialize the positions of the servos
        hookRight.setPosition(hookRightInit);
        hookLeft.setPosition(hookLeftInit);
        //bucket.setPosition(bucketInit);
        rightSki.setPosition(rightSkiInit);
        leftSki.setPosition(leftSkiInit);
        //intakeHeight.setPosition(intakeHeightInit);
        //shelterPeople.setPosition(shelterPeopleInit);



    }

    @Override
    public void loop() {
        //Map controllers to variables
        driverRight = gamepad1.right_stick_y;
        driverLeft = gamepad1.left_stick_y;
        operatorRight = gamepad2.right_stick_y;
        operatorLeft = gamepad2.left_stick_y;
//        float driverTriggerRight = gamepad1.right_trigger;
//        float driverTriggerLeft = gamepad1.left_trigger;
        operatorX = gamepad2.x;
        boolean operatorY = gamepad2.y;
        boolean operatorA = gamepad2.a;
        boolean operatorB = gamepad2.b;
        operatorTriggerRight = gamepad2.right_trigger;
        operatorTriggerLeft = gamepad2.left_trigger;
        operatorBumperRight = gamepad2.right_bumper;
        operatorBumperLeft = gamepad2.left_bumper;
        operatorDpadUP = gamepad2.dpad_up;
        operatorDpadDOWN = gamepad2.dpad_down;





        // clip the right/left values so that the values never exceed +/- 1
        driverRight = Range.clip(driverRight, -1, 1);
        driverLeft = Range.clip(driverLeft, -1, 1);
        operatorRight = Range.clip(operatorRight, -1, 1);
        operatorLeft = Range.clip(operatorLeft, -1, 1);




        // scale the joystick value to make it easier to control
        // the robot more precisely at slower speeds.
        driverRight = (float)scaleInput(driverRight);
        driverLeft = (float)scaleInput(driverLeft);
        operatorRight = (float)scaleInput(operatorRight);
        operatorLeft = (float)scaleInput(operatorLeft);


        // write the values to the motors
        motorRight.setPower(driverRight);
        motorLeft.setPower(driverLeft);
        //motorLeftWinch.setPower(operatorLeft*multiplierLift);
        //motorRightWinch.setPower(operatorLeft*multiplierLift);

        motor118R.setPower(operatorLeft);
        motor118L.setPower(operatorLeft);

        //Operator X for front hook position
        if(operatorX)
        {
            if(hookToggle == false)
            {
                hookLeft.setPosition(hookLeftDeployed);
                hookRight.setPosition(hookRightDeployed);
                hookToggle = true;
            }
            else if(hookToggle == true)
            {
                hookLeft.setPosition(hookLeftOriginal);
                hookRight.setPosition(hookRightOriginal);
                hookToggle = false;
            }
        }

        //Operator Y for Shelter people
//        if(operatorY)
//        {
//            if(shelterPeopleToggle == false)
//            {
//                shelterPeople.setPosition(shelterPeopleDeployed);
//                shelterPeopleToggle = true;
//            }
//            else if(shelterPeopleToggle == true)
//            {
//                shelterPeople.setPosition(shelterPeopleOriginal);
//                shelterPeopleToggle = false;
//            }
//        }
//
//
//        //Operator A for hold/score with bucket
//        if(operatorA)
//        {
//            if(bucketToggle == false)
//            {
//                bucket.setPosition(bucketDeployed);
//                bucketToggle = true;
//            }
//            else if(bucketToggle == true)
//            {
//                bucket.setPosition(bucketOriginal);
//                bucketToggle = false;
//            }
//        }

//
//
//        if(operatorB)
//        {
//            if(intakeHeightToggle == false)
//            {
//                intakeHeight.setPosition(intakeHeightDeployed);
//                intakeHeightToggle = true;
//            }
//            else if(intakeHeightToggle == true)
//            {
//                intakeHeight.setPosition(intakeHeightOriginal);
//                intakeHeightToggle = false;
//            }
//        }



//        //Operator triggers for Jog
//        if(operatorTriggerLeft> 0)
//        {
//            motorJog.setPower(-operatorTriggerLeft*multiplierJog);
//        }
//        if(operatorTriggerRight> 0)
//        {
//            motorJog.setPower(operatorTriggerRight*multiplierJog);
//        }
//        else
//        {
//            motorJog.setPower(0);
//        }
//
//        //Operator bumpers for intake
//        if(operatorBumperLeft)
//        {
//            motorIntake.setPower(-intakeSpeed);
//        }
//        if(operatorBumperRight)
//        {
//            motorIntake.setPower(intakeSpeed);
//        }
//        else
//        {
//            motorIntake.setPower(0);
//        }
//
        //Operator DPAD for Skis
        if(operatorDpadDOWN)
        {
            leftSki.setPosition(leftSkiDeployed);
            rightSki.setPosition(rightSkiDeployed);
        }
        if(operatorDpadUP)
        {
            leftSki.setPosition(leftSkiOriginal);
            rightSki.setPosition(rightSkiOriginal);
            }

    }









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
}
