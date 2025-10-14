package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Mechanisms.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Score;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;
@TeleOp
public class FieldOrientatedOpMode extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    double forward, strafe, rotate;
    double intakePower, outtakePower;
    intake intakeHold = new intake(); //intake

    Arm holdingArm = new Arm();
    Score outtakeScore = new Score(); //score

    @Override
    public void init() {
        drive.init(hardwareMap); //drive
        intakeHold.init(hardwareMap);//intake
        outtakeScore.init(hardwareMap);//outtake
        holdingArm.init(hardwareMap);
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
        //Having a Toggle for the outake makes it easier to operate
        if (gamepad2.x) {
            outtakePower = 1;
        } else if (gamepad2.y) {
            outtakePower = -1;
        } else if (gamepad2.start) {
            outtakePower = 0; //Allows for outtake to be left on so the driver can focus on other elements
        } else if (outtakePower == -1) {
            outtakePower = 0; //Doesn't make sense for the toggle function to apply to spinning the wheel backwards
        }

        if (gamepad2.dpad_right) {
            holdingArm.armPosition(45);
        } else {
            holdingArm.armPosition(0);
        }







        drive.drive(forward, strafe, rotate);
        intakeHold.intakeHold(intakePower);
        outtakeScore.outtakeScore(outtakePower);// all the drive and foward intake and outtake should work
        telemetry.addData("arm encorder", holdingArm.armPosition());
        telemetry.update();
    }
}
