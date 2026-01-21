package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Mechanisms.AutoScore;
import org.firstinspires.ftc.teamcode.Mechanisms.MecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Midtake;
import org.firstinspires.ftc.teamcode.Mechanisms.Score;
import org.firstinspires.ftc.teamcode.Mechanisms.SetSpeed;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;


@TeleOp
public class IndividualMotorTest extends OpMode {
    MecanumDrive drive = new MecanumDrive();
    private DcMotor frontLeft, frontRight, backLeft, backRight, intake, outtake;
    intake intakeHold = new intake(); //intake
    Score outtakeScore = new Score(); //score
    Midtake midtake = new Midtake();

    SetSpeed score = new SetSpeed();
    private ElapsedTime runtime = new ElapsedTime();
    int position = 0;
    int newPosition = 0;
    double velocity = 0;





    double pos, deltaPos, time, deltaTime, rpm, power, deltaPower, targetRPM;




    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "fl");
        frontRight = hardwareMap.get(DcMotor.class, "fr");
        backLeft = hardwareMap.get(DcMotor.class, "bl");
        backRight = hardwareMap.get(DcMotor.class, "br");
        intake = hardwareMap.get(DcMotor.class, "W1");
        outtake = hardwareMap.get(DcMotor.class, "W2");
        power = 0.5;

        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        outtake.setDirection(DcMotor.Direction.REVERSE);
    }

    @Override
    public void loop() {
        targetRPM = 600;
        deltaPos = outtake.getCurrentPosition();
        deltaTime = runtime.milliseconds();


        if (power > 1) {
            power = 1;
        }
        outtake.setPower(power);
        if (gamepad1.a) {
            frontLeft.setPower(1);
        } else if (gamepad1.b) {
            frontRight.setPower(1);
        } else if (gamepad1.x) {
            backRight.setPower(1);
        } else if (gamepad1.y) {
            backLeft.setPower(1);
        } else {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backRight.setPower(0);
            backLeft.setPower(0);
        }
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        pos = deltaPos - outtake.getCurrentPosition();
        time = runtime.milliseconds() - deltaTime;
        rpm = ((pos/20)/(time/60000));
        deltaPower = (targetRPM)/(rpm+1);
        power *= deltaPower;


        telemetry.addData("power",power);
        telemetry.update();
    }
}