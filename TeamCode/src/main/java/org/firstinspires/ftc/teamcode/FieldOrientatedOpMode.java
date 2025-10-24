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
        drive.init(hardwareMap); //drive
        intakeHold.init(hardwareMap);//intake
        outtakeScore.init(hardwareMap);//outtake
    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = gamepad1.right_stick_x;

        intakePower = gamepad2.right_trigger; //intake
        outtakePower = gamepad2.left_trigger;//score
        if (gamepad2.right_bumper) {
            outtakePower = -outtakePower;
        }//flip intake?? not sure if this works needs testing
        if (gamepad2.left_bumper){
            outtakePower = -outtakePower;// same thing here as intake maybe
        }
        drive.drive(forward, strafe, rotate);
        intakeHold.intakeHold(intakePower);
        outtakeScore.outtakeScore(outtakePower);// all the drive and forward intake and outtake should work
    }
}
