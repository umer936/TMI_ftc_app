package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by luke on 1/13/2016.
 */
public class SwitchbackTest extends OpMode {

    DcMotor thing;
    public void init(){
    thing=hardwareMap.dcMotor.get("motor_1");}

    public void loop() {
        thing.setPower(1.0);
    }

}
