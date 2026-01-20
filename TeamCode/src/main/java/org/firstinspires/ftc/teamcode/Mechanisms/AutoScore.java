package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Thread.sleep;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class AutoScore {
    /** has intake and outtake info for autonomous scoring possibly also needed for auto scoring in TeleOp in the future
    **/
    private DcMotor outtake, intake, midtake;


    public void init(HardwareMap hwMap) {
        outtake = hwMap.get(DcMotor.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake = hwMap.get(DcMotor.class, "W1");
        intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        midtake =hwMap.get(DcMotor.class,"W3");
        midtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake.setDirection(DcMotor.Direction.REVERSE);
        outtake.setDirection(DcMotor.Direction.REVERSE);

    }
    public void AutonIntake(double intakePower, double outtakePower) {
        outtake.setPower(outtakePower);
        intake.setPower(intakePower);

    }
    public void AutonScore(double outtakePower, double intakePower, double midtakePower, long millis) throws InterruptedException {

        midtake.setPower(-0.3*midtakePower);
        sleep(200);
        midtake.setPower(0.0);

        outtake.setPower(outtakePower);
        sleep(1000);



        midtake.setPower(0.5*midtakePower);
        intake.setPower(0.5*intakePower);
        sleep(2000);

        /*midtake.setPower(midtakePower);
        intake.setPower(intakePower);
        sleep(500);*/
        outtake.setPower(0);
        intake.setPower(0);
        midtake.setPower(0);
        /*outtake.setPower(outtakePower);
        int time = 0;
        int position = 0;
        int newPosition = 0;
        int velocity = 0;

        while (time < 200) {
            position = outtake.getCurrentPosition();
            time+=1;
            sleep(10);
            newPosition = outtake.getCurrentPosition();
            velocity = newPosition - position;
            if (velocity > 25) {
                midtake.setPower(midtakePower);
                intake.setPower(intakePower);

            } else {
                intake.setPower(0);
                midtake.setPower(0);
            }
        }
        intake.setPower(0);
        midtake.setPower(0);
        outtake.setPower(0  );*/


    }
}
