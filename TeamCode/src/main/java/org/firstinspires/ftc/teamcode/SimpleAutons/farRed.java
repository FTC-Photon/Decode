package org.firstinspires.ftc.teamcode.SimpleAutons;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mechanisms.AutoScore;

@Autonomous
public class farRed extends LinearOpMode {
    DcMotor frontLeft,frontRight,backLeft, backRight, intake, outtake;

    @Override
    public void runOpMode() {
        backLeft = hardwareMap.get(DcMotor.class,"bl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        outtake = hardwareMap.get(DcMotor.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake = hardwareMap.get(DcMotor.class, "W1");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        forward(1,750); //long forward
        turnLeft(1,200);//have ramp face goal ; get angle right
        backward(1,500);//maybe to go towards
        AutonScore(1,1, 1000); //score
        turnLeft(1,500);//face out to leave
        forward(1,500); // get out
    }

    public void turnRight(double power, long mil){
        frontLeft.setPower(power);
        frontRight.setPower(-power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void strafeRight(double power, long mil){
        frontLeft.setPower(power);
        frontRight.setPower(-power);
        backLeft.setPower(power);
        backRight.setPower(-power);


        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void turnLeft(double power, long mil){
        frontLeft.setPower(-power);
        frontRight.setPower(power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
    }
    public void strafeLeft(double power, long mil){
        frontLeft.setPower(-power);
        frontRight.setPower(power);
        backLeft.setPower(-power);
        backRight.setPower(power);


        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
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

    public void backward(double power, long mil) {
        frontLeft.setPower(-power);
        frontRight.setPower(-power);
        backLeft.setPower(-power);
        backRight.setPower(-power);

        sleep(mil);

        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
    public void AutonScore(double outtakePower, double intakePower, long millis)  {
        intake.setPower(0);
        outtake.setPower(outtakePower);
        sleep(1000);
        intake.setPower(intakePower);
        sleep(750);
        intake.setPower(0);

        sleep(500);
        intake.setPower(-0.35);
        sleep(500);
        intake.setPower(intakePower);

        sleep(millis);
        intake.setPower(0);
        outtake.setPower(0);
    }
}
