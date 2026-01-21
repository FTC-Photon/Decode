package org.firstinspires.ftc.teamcode.Mechanisms;

import static java.lang.Thread.sleep;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class SetSpeed {
    /** has intake and outtake info for autonomous scoring possibly also needed for auto scoring in TeleOp in the future
     **/
    private DcMotor outtake;


    public void init(HardwareMap hwMap) {
        outtake = hwMap.get(DcMotor.class, "W2");
        outtake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        outtake.setDirection(DcMotor.Direction.REVERSE);

    }


    public void OuttakeSpeed(double targetRPM, long millis) throws InterruptedException{

        ElapsedTime runtime = new ElapsedTime();
        outtake.setPower(1.0);


        double pos, deltaPos, time, deltaTime, rpm, power, deltaPower;
        power = 0.5;

            deltaPos = outtake.getCurrentPosition();
            deltaTime = runtime.milliseconds();
            sleep(100);
            pos = outtake.getCurrentPosition() - deltaPos;
            time = runtime.milliseconds() - deltaTime;
            rpm = ((pos/20)/(time/60000));
            deltaPower = (targetRPM+100)/(rpm+100);
            power *= deltaPower;
            if (power > 1) {
                power = 1;
            }
            outtake.setPower(power);







        }


    }


