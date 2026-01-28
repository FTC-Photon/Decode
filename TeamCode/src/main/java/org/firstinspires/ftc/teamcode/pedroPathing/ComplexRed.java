package org.firstinspires.ftc.teamcode.pedroPathing;

import static android.os.SystemClock.sleep;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.pedropathing.util.Timer;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Mechanisms.AutoScore;
import org.firstinspires.ftc.teamcode.Mechanisms.Limelight;
import org.firstinspires.ftc.teamcode.Mechanisms.intake;

@Autonomous
public class ComplexRed extends OpMode {
    private Follower follower;
    private Timer pathTimer, actionTimer, opmodeTimer;
    private int pathState = 0;
    private final Pose startPose = new Pose(110, 135, Math.toRadians(180)); // Start Pose currently left corner subject to change
    private final Pose AprilTagPose = new Pose(110,91, Math.toRadians(-90));
    private final Pose scorePose = new Pose(110, 91, Math.toRadians(-135)); // Scoring Pose of our robot. It is facing the goal at a 135 degree angle.

    private final Pose setup1Pose = new Pose(94,84,Math.toRadians(0)); // setup for the following pose
    private final Pose pickup1Pose = new Pose(122, 84, Math.toRadians(0)); // Highest (First Set) of Artifacts from the Spike Mark.

    private final Pose setup2Pose = new Pose(94,60,Math.toRadians(0)); // setup for the following pose
    private final Pose pickup2Pose = new Pose(122, 60, Math.toRadians(0)); // Middle (Second Set) of Artifacts from the Spike Mark.

    private final Pose setup3Pose = new Pose(94,36,Math.toRadians(0)); // setup for the following pose
    private final Pose pickup3Pose = new Pose(122, 36, Math.toRadians(0));// Lowest (Third Set) of Artifacts from the Spike Mark.
    private Path scorePreload;
    private PathChain grabPickup1, goSetup1, scorePickup1, grabPickup2, goSetup2, scorePickup2,grabPickup3, goSetup3, scorePickup3, readTag, correction;
    int tagID, pickups;
    long wait = 500;
    double out = 1850;
    AutoScore autoScore = new AutoScore(); //intake and outtake to score???

    Limelight limelight = new Limelight();

    intake intake = new intake(); //intake alone
    public void buildPaths() {
        /* This is our scorePreload path. We are using a BezierLine, which is a straight line. */
        readTag = follower.pathBuilder()
                .addPath(new BezierLine(startPose, AprilTagPose))
                .setLinearHeadingInterpolation(startPose.getHeading(), AprilTagPose.getHeading())
                .build();

        scorePreload = new Path(new BezierLine(AprilTagPose, scorePose));
        scorePreload.setLinearHeadingInterpolation(AprilTagPose.getHeading(), scorePose.getHeading());

        /* This is our grabPickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        goSetup1 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, setup1Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), setup1Pose.getHeading())
                .build();
        //path to the middle point
        grabPickup1 = follower.pathBuilder()
                .addPath(new BezierLine(setup1Pose, pickup1Pose))
                .setLinearHeadingInterpolation(setup1Pose.getHeading(), pickup1Pose.getHeading())
                .build();

        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup1 = follower.pathBuilder()
               .addPath(new BezierLine(pickup1Pose, scorePose))
                .setLinearHeadingInterpolation(pickup1Pose.getHeading(), scorePose.getHeading())
                .build();

        goSetup2 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, setup2Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), setup2Pose.getHeading())
                .build();
        //path to the middle point
        grabPickup2 = follower.pathBuilder()
                .addPath(new BezierLine(setup2Pose, pickup2Pose))
                .setLinearHeadingInterpolation(setup2Pose.getHeading(), pickup2Pose.getHeading())
                .build();

        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup2 = follower.pathBuilder()
                .addPath(new BezierLine(pickup2Pose, scorePose))
                .setLinearHeadingInterpolation(pickup2Pose.getHeading(), scorePose.getHeading())
                .build();
        /* This is our grabPickup2 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        goSetup3 = follower.pathBuilder()
                .addPath(new BezierLine(scorePose, setup3Pose))
                .setLinearHeadingInterpolation(scorePose.getHeading(), setup3Pose.getHeading())
                .build();
        //path to the middle point
        grabPickup3 = follower.pathBuilder()
                .addPath(new BezierLine(setup3Pose, pickup3Pose))
                .setLinearHeadingInterpolation(setup3Pose.getHeading(), pickup3Pose.getHeading())
                .build();

        /* This is our scorePickup1 PathChain. We are using a single path with a BezierLine, which is a straight line. */
        scorePickup3 = follower.pathBuilder()
                .addPath(new BezierLine(pickup3Pose, scorePose))
                .setLinearHeadingInterpolation(pickup3Pose.getHeading(), scorePose.getHeading())
                .build();
    }

    public void autonomousPathUpdate() throws InterruptedException {
        switch (pathState) {
            //Read Tag
            case 0:
                follower.followPath(readTag,true);
                pickups = 0;

                if(!follower.isBusy()) {
                    tagID = limelight.getTagID();

                    follower.followPath(scorePreload);
                    setPathState(1);
                }
                break;
            //Score Preload
            case 1:

                if(!follower.isBusy()) {
                    /* Score Preload */
                    autoScore.AutonScore(out,1,1);
                    pickups+=1;
                    if (pickups == 1) {
                        if (tagID == 21) {
                            follower.followPath(goSetup1, true);
                            setPathState(5);
                        } else if (tagID == 22) {
                            follower.followPath(goSetup2, true);
                            setPathState(2);
                        } else { //tagID is 23
                            follower.followPath(goSetup3, true);
                            setPathState(8);
                        }
                    } else if (pickups == 2) {
                        if (tagID == 21) {
                            follower.followPath(goSetup1, true);
                            setPathState(2);
                        } else if (tagID == 22) {
                            follower.followPath(goSetup2, true);
                            setPathState(8);
                        } else {//tagID is 23
                            follower.followPath(goSetup3, true);
                            setPathState(5);
                        }
                    } else {
                        stop();
                    }
                }
                break;

            //Got to setup Position for line 1
            case 2:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(grabPickup1, true);
                    autoScore.AutonIntake(1.0,-out*0.2);

                    setPathState(3);
               }
                break;
            case 3:

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    sleep(wait);
                    autoScore.AutonIntake(0.0,0.0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(scorePickup1,true);

                    setPathState(1);
                }
                break;

                //Line 2
            case 5:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(grabPickup2, true);
                    autoScore.AutonIntake(1.0,-out*0.2);

                    setPathState(6);
                }
                break;
            case 6:

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    sleep(wait);
                    autoScore.AutonIntake(0.0,0.0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(scorePickup2,true);
                    setPathState(1);
                }
                break;


            //Line 3
            case 8:
                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the pickup1Pose's position */
                if(!follower.isBusy()) {
                    /* Grab Sample */

                    /* Since this is a pathChain, we can have Pedro hold the end point while we are scoring the sample */
                    follower.followPath(grabPickup3, true);
                    autoScore.AutonIntake(1.0,-out*0.2);
                    setPathState(9);
                }
                break;
            case 9:

                /* This case checks the robot's position and will wait until the robot position is close (1 inch away) from the scorePose's position */
                if(!follower.isBusy()) {
                    /* Score Sample */
                    sleep(wait);
                    autoScore.AutonIntake(0.0,0.0);
                    /* Since this is a pathChain, we can have Pedro hold the end point while we are grabbing the sample */
                    follower.followPath(scorePickup3,true);
                    setPathState(1);
                }
                break;

            case 10:
                Pose LLpose;
                LLpose = limelight.getPosition();
                if (!(LLpose == null)) {
                    correction = follower.pathBuilder()
                            .addPath(new BezierLine(LLpose, scorePose))
                            .setLinearHeadingInterpolation(LLpose.getHeading(), scorePose.getHeading())
                            .build();
                    follower.followPath(correction);
                    if (!follower.isBusy()) {
                        setPathState(1);
                    }
                } else{
                    setPathState(1);
                }
        }
    }

    /** These change the states of the paths and actions. It will also reset the timers of the individual switches **/
    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
    }
    /** This is the main loop of the OpMode, it will run repeatedly after clicking "Play". **/
    @Override
    public void loop() {

        // These loop the movements of the robot, these must be called continuously in order to work
        follower.update();
        try {
            autonomousPathUpdate();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        // Feedback to Driver Hub for debugging
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("velocity",autoScore.getOuttakeVelocity());
        telemetry.update();
    }

    /** This method is called once at the init of the OpMode. **/
    @Override
    public void init() {
        pathTimer = new Timer();
        opmodeTimer = new Timer();
        opmodeTimer.resetTimer();


        follower = Constants.createFollower(hardwareMap);
        buildPaths();
        follower.setStartingPose(startPose);

        intake.init(hardwareMap);
        autoScore.init(hardwareMap);


    }

    /** This method is called continuously after Init while waiting for "play". **/
    @Override
    public void init_loop() {}

    /** This method is called once at the start of the OpMode.
     * It runs all the setup actions, including building paths and starting the path system **/
    @Override
    public void start() {
        opmodeTimer.resetTimer();
        setPathState(0);
    }

    /** We do not use this because everything should automatically disable **/
    @Override
    public void stop() {}
}