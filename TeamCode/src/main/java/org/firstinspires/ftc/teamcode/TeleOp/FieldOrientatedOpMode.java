package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Score;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Midtake;
import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;

@TeleOp
public class FieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    double intakePower, outtakePower, midPower;
    boolean slideMode, slidePressed, driverMode, driverPressed = false;

    GoBildaPrismDriver prism;

    intake intakeHold = new intake(); //intake
    Score outtakeScore = new Score(); //score
    Midtake midtake = new Midtake();


    @Override
    public void init() {
        drive.init(hardwareMap); //drive
        intakeHold.init(hardwareMap);//intake
        outtakeScore.init(hardwareMap);//outtake
        midtake.init(hardwareMap);
        slideMode = true;
    }

    @Override
    public void loop() {
        if(driverMode) {
            forward = gamepad1.left_stick_y;
            strafe = gamepad1.left_stick_x;
        } else {
            forward = -gamepad1.left_stick_y;
            strafe = -gamepad1.left_stick_x;
        }

        rotate = -gamepad1.right_stick_x;
        //toggle for the driver mode
        if (gamepad1.dpad_down && !driverPressed) {
            if (driverMode) {
                driverMode = false;
            } else {
                driverMode = true;
            }
            driverPressed = true;
        }
        if (!gamepad1.dpad_down){
            driverPressed = false;
        }
        //toggle for the slide mode
        if (gamepad2.dpad_down && !slidePressed) {
            if (slideMode) {
                slideMode = false;
            } else {
                slideMode = true;
            }
            slidePressed = true;
        }
        if (!gamepad2.dpad_down) {
            slidePressed = false;
        }



        if (slideMode) {
            if (gamepad2.a) {
                intakePower = -1;
                midPower = 0.5;
            } else if (gamepad2.b) {
                intakePower = 1;
                midPower = -0.5;
            } else if (gamepad1.b){
                intakePower = 1;
                midPower = -0.5;
            } else if(gamepad1.a){
                intakePower = -1;
                midPower = 0.5;
            }else {
                intakePower=0;
                midPower = 0;
            }
            outtakePower = -0.1;
        } else {
            if (gamepad2.x) {
                outtakePower = -1;
            } else if (gamepad2.y) {
                outtakePower = 1;
            } else if (gamepad1.x) {
                outtakePower = -1;
            } else if(gamepad1.y){
                outtakePower=1;
            } else{
                outtakePower = 0;
            }
            if (gamepad2.a) {

                midPower = 1;
            } else if (gamepad2.b) {
                intakePower = 1;
                midPower = -1;
            } else if (gamepad1.b){
                intakePower = 1;
                midPower = -1;
            } else if(gamepad1.a){

                midPower = 1;
            }else {
                intakePower=0;
                midPower = 0;
            }
        }









        drive.drive(forward, strafe, rotate);
        intakeHold.intakeHold(intakePower);
        midtake.midtakeHold(midPower);
        outtakeScore.outtakeScore(outtakePower);
        telemetry.addData("slideInLaunchMode:",slideMode);
        telemetry.addData("DriverInLaunchMode:",driverMode);
        telemetry.update();
    }
}