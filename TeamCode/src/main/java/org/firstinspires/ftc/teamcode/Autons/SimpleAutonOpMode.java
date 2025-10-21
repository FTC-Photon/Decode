package org.firstinspires.ftc.teamcode.Autons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous
public class SimpleAutonOpMode extends LinearOpMode {
    DcMotor backLeft = hardwareMap.get(DcMotor.class,"bl");
    DcMotor backRight = hardwareMap.get(DcMotor.class, "br");
    DcMotor frontLeft = hardwareMap.get(DcMotor.class, "fl");
    DcMotor frontRight = hardwareMap.get(DcMotor.class, "fr");


    @Override
    public void runOpMode() throws InterruptedException {
        turnLeft(0.3, 500); //ex
    }

    public void turnRight(double power, long mil){
        frontLeft.setPower(power);
        frontRight.setPower(-power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    public void turnLeft(double power, long mil){
        frontLeft.setPower(-power);
        frontRight.setPower(power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
    }

    public void forward(double power, long mil){
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
