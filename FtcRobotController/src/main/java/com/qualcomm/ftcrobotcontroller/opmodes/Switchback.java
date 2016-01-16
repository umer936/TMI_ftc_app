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
    DcMotor motorLift;
    DcMotor motorJog;
    DcMotor motorIntake;
    Servo hookRight;
    Servo hookLeft;
    Servo bucket;
    Servo intakeHeight;
    Servo shelterPeople;

    // Toggle variables
    public boolean bucketToggle = false;
    public boolean shelterPeopleToggle = false;
    public boolean hookToggle = false;
    public boolean intakeHeightToggle = false;

    //Initialization positions for Servos
    double hookLeftInit = 0.5;
    double hookRightInit = 0.5;
    double shelterPeopleInit = 0.5;
    double bucketInit = 0.5;
    double intakeHeightInit = 0.5;

    //Multipliers for speed modifications
    float multiplierDrive = 1;
    float multiplier118 = 1;
    float multiplierLift = 1;
    float multiplierJog = 1;

    //Speed for fixed-speed intake
    float intakeSpeed = 1;

    //Original and Deployed Position Variables
    float hookRightOriginal = 1;
    float hookRightDeployed = 0;

    float hookLeftOriginal = 1;
    float hookLeftDeployed = 0;

    float bucketOriginal = 1;
    float bucketDeployed = 0;

    float intakeHeightOriginal = 1;
    float intakeHeightDeployed = 0;

    float shelterPeopleOriginal = 1;
    float shelterPeopleDeployed = 0;




    // Constructor
    Switchback(){}

    @Override
    public void init(){
        // Define Motor mapping to variables
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_2");
        motor118L = hardwareMap.dcMotor.get("motor_3");
        motor118R = hardwareMap.dcMotor.get("motor_4");
        motorIntake = hardwareMap.dcMotor.get("motor_5");
        motorJog = hardwareMap.dcMotor.get("motor_6");
        motorLift = hardwareMap.dcMotor.get("motor_7");

        //Define Servo mapping to variables
        hookLeft = hardwareMap.servo.get("servo_1");
        hookRight = hardwareMap.servo.get("servo_2");
        bucket = hardwareMap.servo.get("servo_3");
        intakeHeight = hardwareMap.servo.get("servo_4");
        shelterPeople = hardwareMap.servo.get("servo_5");

        //Initialize the positions of the servos
        hookRight.setPosition(hookRightInit);
        hookLeft.setPosition(hookLeftInit);
        bucket.setPosition(bucketInit);
        intakeHeight.setPosition(intakeHeightInit);
        shelterPeople.setPosition(shelterPeopleInit);
    }

    @Override
    public void loop() {
        //Map controllers to variables
        float driverRight = gamepad1.left_stick_y;
        float driverLeft = gamepad1.right_stick_y;
        float operatorRight = gamepad2.right_stick_y;
        float operatorLeft = gamepad2.left_stick_y;

        float driverTriggerRight = gamepad1.right_trigger;
        float driverTriggerLeft = gamepad1.left_trigger;
        boolean operatorX = gamepad2.x;
        boolean operatorY = gamepad2.y;
        boolean operatorA = gamepad2.a;
        boolean operatorB = gamepad2.b;
        float operatorTriggerRight = gamepad2.right_trigger;
        float operatorTriggerLeft = gamepad2.left_trigger;
        boolean operatorBumperRight = gamepad2.right_bumper;
        boolean operatorBumperLeft = gamepad2.left_bumper;




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
        motorRight.setPower(driverRight*multiplierDrive);
        motorLeft.setPower(driverLeft*multiplierDrive);
        motorLift.setPower(operatorLeft*multiplierLift);
        motor118R.setPower(operatorTriggerRight*multiplier118);
        motor118L.setPower(operatorTriggerLeft*multiplier118);

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
        if(operatorY)
        {
            if(shelterPeopleToggle == false)
            {
                shelterPeople.setPosition(shelterPeopleDeployed);
                shelterPeopleToggle = true;
            }
            else if(shelterPeopleToggle == true)
            {
                shelterPeople.setPosition(shelterPeopleOriginal);
                shelterPeopleToggle = false;
            }
        }

        //Operator A for hold/score with bucket
        if(operatorA)
        {
            if(bucketToggle == false)
            {
                bucket.setPosition(bucketDeployed);
                bucketToggle = true;
            }
            else if(bucketToggle == true)
            {
                bucket.setPosition(bucketOriginal);
                bucketToggle = false;
            }
        }

        if(operatorB)
        {
            if(intakeHeightToggle == false)
            {
                intakeHeight.setPosition(intakeHeightDeployed);
                intakeHeightToggle = true;
            }
            else if(intakeHeightToggle == true)
            {
                intakeHeight.setPosition(intakeHeightOriginal);
                intakeHeightToggle = false;
            }
        }

        //Operator triggers for Jog
        if(operatorTriggerLeft> 0)
        {
            motorJog.setPower(-operatorTriggerLeft*multiplierJog);
        }
        if(operatorTriggerRight> 0)
        {
            motorJog.setPower(operatorTriggerRight*multiplierJog);
        }
        else
        {
            motorJog.setPower(0);
        }

        //Operator bumpers for intake
        if(operatorBumperLeft)
        {
            motorIntake.setPower(-intakeSpeed*m);
        }
        if(operatorBumperRight)
        {
            motorIntake.setPower(intakeSpeed);
        }
        else
        {
            motorIntake.setPower(0);
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
    @Override
    public void stop(){}
}
