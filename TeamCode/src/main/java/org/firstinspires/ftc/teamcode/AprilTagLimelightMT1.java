package org.firstinspires.ftc.teamcode;

import com.pedropathing.ftc.FTCCoordinates;
import com.pedropathing.geometry.PedroCoordinates;
import com.pedropathing.geometry.Pose;
import com.qualcomm.hardware.limelightvision.LLResult;
import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.Pose3D;

@Autonomous
public class AprilTagLimelightMT1 extends OpMode{
    private Limelight3A limelight;
    private IMU imu;


@Override
    public void init() {
        limelight = hardwareMap.get(Limelight3A.class, "Limelight");
        limelight.pipelineSwitch(1);
        //april tag pipeline changes in the limelight setup
        imu = hardwareMap.get(IMU.class, "imu");
        RevHubOrientationOnRobot revHubOrientationOnRobot = new RevHubOrientationOnRobot(RevHubOrientationOnRobot.LogoFacingDirection.UP, RevHubOrientationOnRobot.UsbFacingDirection.FORWARD);
        imu.initialize(new IMU.Parameters(revHubOrientationOnRobot));
    }
    @Override
    public void start(){
        limelight.start();
    }
@Override
    public void loop(){
        LLResult result = limelight.getLatestResult();
        if (result != null && result.isValid()) {
            Pose3D botpose = result.getBotpose();
                double x = botpose.getPosition().x;
                double y = botpose.getPosition().y;
                double yaw = botpose.getOrientation().getYaw();
                telemetry.addData("MT1 Location FTC", "(" + x + ", " + y + ")");
                final Pose limelightPose = new Pose(x, y, yaw, FTCCoordinates.INSTANCE).getAsCoordinateSystem(PedroCoordinates.INSTANCE);
               double X = limelightPose.getX();
               double Y = limelightPose.getY();
            telemetry.addData("MT1 Location Pedro", "(" + X + ", " + Y + ")");
        }
    }



  /*  private double getDistanceFromTag(double ta) {
        //distance is the hypotenuse
        double scale = 128.9873; // = c value in equation of curve c/x
        double distance = (scale/ta) ;
        return distance;
    }*/
}
   /* private Pose getRobotPoseFromCamera() {
        YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();
        limelight.updateRobotOrientation(orientation.getYaw(AngleUnit.DEGREES));
        LLResult result = limelight.getLatestResult();
        double x = 0, y=0;
        if (result != null && result.isValid()){
            Pose3D botpose = result.getBotpose();
            x = botpose.getPosition().x;
            y= botpose.getPosition().y;
        }
        final Pose pose = new Pose(x, y, 0, FTCCoordinates.INSTANCE).getAsCoordinateSystem(PedroCoordinates.INSTANCE);
        return pose;
    }
}*/
