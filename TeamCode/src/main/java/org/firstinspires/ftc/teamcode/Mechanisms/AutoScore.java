package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoScore {
    /** has intake and outtake info for autonomous scoring possibly also needed for auto scoring in TeleOp in the future
    **/
    private DcMotor outtake, intake;

    public void init(HardwareMap hwMap) {
        outtake = hwMap.get(DcMotor.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake = hwMap.get(DcMotor.class, "W1");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void AutonScore(double outtakePower, double intakePower, long millis) throws InterruptedException {
        intake.setPower(intakePower);
        outtake.setPower(outtakePower);
        sleep(millis);
        intake.setPower(0);
        outtake.setPower(0);
        sleep(500);
        outtake.setPower(-outtakePower);
        sleep(1000);
        intake.setPower(intakePower);
        outtake.setPower(outtakePower);
        sleep(millis);
    }
}
