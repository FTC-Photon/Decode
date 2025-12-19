package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver.LayerHeight;
import static org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver.Artboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Prism.Color;
import org.firstinspires.ftc.teamcode.Prism.GoBildaPrismDriver;
import org.firstinspires.ftc.teamcode.Prism.PrismAnimations;


import java.util.concurrent.TimeUnit;

@TeleOp(name="PrismModeWrite", group="Linear OpMode")
//@Disabled

public class PrismModeWrite extends LinearOpMode {

    GoBildaPrismDriver prism;

    @Override
    public void runOpMode() {

        prism = hardwareMap.get(GoBildaPrismDriver.class, "prism");

        telemetry.addData("Device ID: ", prism.getDeviceID());
        telemetry.addData("Firmware Version: ", prism.getFirmwareVersionString());
        telemetry.addData("Hardware Version: ", prism.getHardwareVersionString());
        telemetry.addData("Power Cycle Count: ", prism.getPowerCycleCount());
        telemetry.addData("Run Time (Minutes): ", prism.getRunTime(TimeUnit.MINUTES));
        telemetry.update();

        prism.enableDefaultBootArtboard(true);
        prism.setStripLength(23);

        PrismAnimations.Solid solid = new PrismAnimations.Solid(Color.YELLOW);
        PrismAnimations.DroidScan droidScan = new PrismAnimations.DroidScan(Color.CYAN);
        PrismAnimations.Pulse pulse = new PrismAnimations.Pulse(Color.GREEN);


        // Wait for the game to start (driver presses START)
        waitForStart();
        resetRuntime();

        solid.setBrightness(100);
        solid.setStartIndex(0);
        solid.setStopIndex(23);

        droidScan.setBrightness(100);
        droidScan.setStartIndex(0);
        droidScan.setStopIndex(23);

        pulse.setBrightness(100);

        // Artboard 0: Slide off Driver off

        prism.insertAndUpdateAnimation(LayerHeight.LAYER_0, solid);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_1, droidScan);
        prism.loadAnimationsFromArtboard(Artboard.ARTBOARD_0);

        // Artboard 1: Slide on Driver off

        prism.insertAndUpdateAnimation(LayerHeight.LAYER_0, solid);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_1, droidScan);
        // top-right green indicates slide mode on
        pulse.setStartIndex(8);
        pulse.setStopIndex(11);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_2, pulse);

        prism.loadAnimationsFromArtboard(Artboard.ARTBOARD_1);

        // Artboard 2: Slide off Driver on

        prism.insertAndUpdateAnimation(LayerHeight.LAYER_0, solid);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_1, droidScan);
        // top-left green indicates driver mode on
        pulse.setStartIndex(12);
        pulse.setStopIndex(15);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_2, pulse);

        prism.loadAnimationsFromArtboard(Artboard.ARTBOARD_2);

        // Artboard 3: Slide on Driver on

        prism.insertAndUpdateAnimation(LayerHeight.LAYER_0, solid);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_1, droidScan);
        // top-right green indicates slide mode on
        pulse.setStartIndex(8);
        pulse.setStopIndex(11);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_2, pulse);
        // top-left green indicates driver mode on
        pulse.setStartIndex(12);
        pulse.setStopIndex(15);
        prism.insertAndUpdateAnimation(LayerHeight.LAYER_3, pulse);

        prism.loadAnimationsFromArtboard(Artboard.ARTBOARD_2);


        telemetry.addLine("4 Artboards set");
        telemetry.update();
    }
}