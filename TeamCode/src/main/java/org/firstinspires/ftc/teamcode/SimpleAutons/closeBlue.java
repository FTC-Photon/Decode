package org.firstinspires.ftc.teamcode.SimpleAutons;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mechanisms.AutoScore;

@Autonomous
public class closeBlue extends LinearOpMode {
    DcMotor backLeft = hardwareMap.get(DcMotor.class,"bl");
    DcMotor backRight = hardwareMap.get(DcMotor.class, "br");
    DcMotor frontLeft = hardwareMap.get(DcMotor.class, "fl");
    DcMotor frontRight = hardwareMap.get(DcMotor.class, "fr");
    AutoScore autoScore = new AutoScore();


    @Override
    public void runOpMode() throws InterruptedException {
        forward(1, 500); // first moves away from goal
        strafeLeft(1,500);// strafe left to position in front of goal
        turnRight(1,300);//has to turn slightly right to get angle
        autoScore.AutonScore(1,1,1000); // made up numbers that whole class needs to be checked
        forward(1,500); // to get out of area?? need to check how to score auton points
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
}

