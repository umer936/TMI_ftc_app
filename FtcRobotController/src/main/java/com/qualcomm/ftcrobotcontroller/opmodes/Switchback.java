package com.qualcomm.ftcrobotcontroller.opmodes;

/**
 * Created by luke on 1/13/2016.
 */
public class Switchback extends OpMode {

    //initialize motors and servos
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

    //toggle variables
    public boolean bucketToggle = false;
    public boolean shelterPeopleToggle = false;
    public boolean hookToggle = false;

    //initialize toggle variables' positions
    double hookPosition;
    double shelterPosition;
    double bucketPosition;

    //idk from TankTeleOp
    public int direction = 1;

    //contsructor
    Switchback(){}

    @Override
    public void init(){
        //we dont know which is really which yet
        motorRight = hardwareMap.dcMotor.get("motor_1");
        motorLeft = hardwareMap.dcMotor.get("motor_2");
        motor118L = hardwareMap.dcMotor.get("motor_3");
        motor118R = hardwareMap.dcMotor.get("motor_4");
        motorIntake = hardwareMap.dcMotor.get("motor_5");
        motorJog = hardwareMap.dcMotor.get("motor_6");
        motorLift = hardwareMap.dcMotor.get("motor_7");
        hookLeft = hardwareMap.servo.get("servo_1");
        hookRight = hardwareMap.servo.get("servo_2");
        bucket = hardwareMap.servo.get("servo_3");
        intakeHeight = hardwareMap.servo.get("servo_4");
        shelterPeople = hardwareMap.servo.get("servo_5");
    }



}
