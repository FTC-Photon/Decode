package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Arm {
    private static DcMotor arm;
    private double armPower;
    private int armPositionOne, armPositionTwo;



    public static void init(HardwareMap map){
        arm = map.get(DcMotor.class, "W3");
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public static void armPosition() {
    }

}
