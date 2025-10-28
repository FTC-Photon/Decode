package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Score {

    double outtakePower;
    private DcMotor outtake;

    public void init(HardwareMap hwMap) {
        outtake = hwMap.get(DcMotor.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void outtakeScore(double outtakePower){
        outtake.setPower(outtakePower);
    }
    public void autoOuttake(double outtakePower, long millis) throws InterruptedException {
        outtake.setPower(outtakePower);
        sleep(millis);
    }

}


