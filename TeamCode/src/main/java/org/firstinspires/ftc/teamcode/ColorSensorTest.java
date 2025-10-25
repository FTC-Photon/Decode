package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Mechanisms.ColorSensor;
@TeleOp
public class ColorSensorTest extends OpMode {
    ColorSensor colorSensor = new ColorSensor();
    ColorSensor.DetectedColor detectedColor;
    @Override
    public void init() {
        colorSensor.init(hardwareMap);
    }

    @Override
    public void loop() {
    detectedColor = colorSensor.getDetectedColor(telemetry);
    telemetry.addData("Color Detected", detectedColor);
    }
}
