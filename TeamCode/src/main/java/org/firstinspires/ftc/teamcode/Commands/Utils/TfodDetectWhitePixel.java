package org.firstinspires.ftc.teamcode.Commands.Utils;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;

import java.util.List;


public class TfodDetectWhitePixel extends CommandBase {

    private final Vision_Subsystem vss;

    public TfodDetectWhitePixel(Vision_Subsystem vss) {
        this.vss = vss;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {

        List<Recognition> currentRecognitions = vss.tfod.getRecognitions();

        for (Recognition recognition : currentRecognitions) {
            double x = (recognition.getLeft() + recognition.getRight()) / 2;
            double y = (recognition.getTop() + recognition.getBottom()) / 2;
        }
    }

    @Override
    public void end(boolean interrupted) {

    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
