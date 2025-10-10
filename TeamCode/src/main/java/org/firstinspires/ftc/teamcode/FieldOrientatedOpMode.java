package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Score;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;
@TeleOp
public class FieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    double intakePower, outtakePower;
    intake intakeHold = new intake(); //intake
    Score outtakeScore = new Score(); //score

    @Override
    public void init() {
        drive.init(hardwareMap);
        intakeHold.init(hardwareMap);//intake
        outtakeScore.init(hardwareMap);
    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        if(gamepad1.a){
            intakePower = 1;
        } else {
         intakePower =0;}

        if(gamepad1.x){
            outtakePower =1;}
        else {
        outtakePower =0;
        }

        //intakePower = gamepad1.right_trigger; //intake
       //outtakePower = gamepad1.left_trigger; //score

        drive.driveFieldRelative(forward, strafe, rotate);
    }
}
