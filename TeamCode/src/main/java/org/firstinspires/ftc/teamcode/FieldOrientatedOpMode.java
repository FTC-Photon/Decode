package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Midtake;
import org.firstinspires.ftc.teamcode.Mechanisms.Score;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;
@TeleOp
public class FieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    double intakePower, outtakePower, midtakePower;
    intake intakeHold = new intake(); //intake
    Midtake midtakeHold = new Midtake();
    Arm holdingArm = new Arm();
    Score outtakeScore = new Score(); //score

    @Override
    public void init() {
        drive.init(hardwareMap); //drive
        intakeHold.init(hardwareMap);//intake
        outtakeScore.init(hardwareMap);//outtake
        holdingArm.init(hardwareMap);
        midtakeHold.init(hardwareMap);
    }

    @Override
    public void loop() {
        forward = gamepad1.left_stick_y;
        strafe = gamepad1.left_stick_x;
        rotate = -gamepad1.right_stick_x;

        //Use buttons for the intake
        if (gamepad2.a) {
            intakePower = 1;
        } else if (gamepad2.b) {
            intakePower = -1;
        } else {
            intakePower = 0;
        }

        if (gamepad1.a) {
            intakePower = 1;
        } else if (gamepad1.b) {
            intakePower = -1;
        } else {
            intakePower = 0;
        }

        if (gamepad2.x) {
            outtakePower = 1;
        } else if (gamepad2.y) {
            outtakePower = -1;
        } else {
            outtakePower = 0;
        }

        if (gamepad1.x) {
            outtakePower = 1;
        } else if (gamepad1.y) {
            outtakePower = -1;
        } else {
            outtakePower = 0;
        }

        if (gamepad2.dpad_right) {
            holdingArm.armPosition(45);
        } else {
            holdingArm.armPosition(0);
        }


        if(gamepad1.dpad_up){
            midtakePower = 1;
        }else if(gamepad1.dpad_down){
            midtakePower = -1;
        } else{
            midtakePower = 0;
        }




        drive.drive(forward, strafe, rotate);
        intakeHold.intakeHold(intakePower);
        outtakeScore.outtakeScore(outtakePower);// all the drive and foward intake and outtake should work
        midtakeHold.midtakeHold(midtakePower);
        telemetry.addData("arm encorder", outtakeScore.scorePosition());
        telemetry.update();
    }
}
