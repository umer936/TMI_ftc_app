package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

/**
 * Created by luke on 1/13/2016.
 */
public class SwitchbackTest extends OpMode {


    DcMotor driveRight;
    DcMotor driveLeft;
    DcMotor R118;
    DcMotor L118;

    public void init(){
        driveRight = hardwareMap.dcMotor.get("right");
        driveLeft = hardwareMap.dcMotor.get("left");
        R118 = hardwareMap.dcMotor.get("R118");
        L118 = hardwareMap.dcMotor.get("L118");
        driveRight.setDirection(DcMotor.Direction.REVERSE);
        R118.setDirection(DcMotor.Direction.REVERSE);
    }

    public void loop() {
//        driveRight.setPower(1.0);
//        driveLeft.setPower(1.0);
//        L118.setPower(.1);
    }

}
