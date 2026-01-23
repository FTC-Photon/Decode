package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Thread.sleep;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class AutoScore {
    /** has intake and outtake info for autonomous scoring possibly also needed for auto scoring in TeleOp in the future
    **/
    private DcMotorEx outtake, intake, midtake;


    public void init(HardwareMap hwMap) {
        outtake = hwMap.get(DcMotorEx.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake = hwMap.get(DcMotorEx.class, "W1");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        midtake =hwMap.get(DcMotorEx.class,"W3");
        midtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setDirection(DcMotor.Direction.REVERSE);
        outtake.setDirection(DcMotor.Direction.REVERSE);

    }
    public void AutonIntake(double intakePower, double outtakePower) {
        outtake.setPower(outtakePower);
        intake.setPower(intakePower);

    }
    public void AutonScore(double outtakeVelocity, double intakePower, double midtakePower, long millis) throws InterruptedException {

        midtake.setPower(-0.3*midtakePower);
        sleep(200);
        midtake.setPower(0.0);

        outtake.setVelocity(outtakeVelocity);
        sleep(1000);



        midtake.setPower(0.5*midtakePower);
        intake.setPower(0.5*intakePower);
        sleep(2000);


        outtake.setPower(0);
        intake.setPower(0);
        midtake.setPower(0);



    }


}
