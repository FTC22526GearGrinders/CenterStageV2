package org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildStageDoorLRTape extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;


    public BuildStageDoorLRTape(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {


        boolean trussSideTapeRed = ActiveMotionValues.getRedAlliance() &&
                ActiveMotionValues.getLcrpos() == 3;

        boolean trussSideTapeBlue = !ActiveMotionValues.getRedAlliance() &&
                ActiveMotionValues.getLcrpos() == 1;

        boolean trussSideTape = trussSideTapeRed || trussSideTapeBlue;

        if (ActiveMotionValues.getSecondPixel() && trussSideTape) {


            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .waitSeconds(ActiveMotionValues.getStopSecs())

                    .lineToLinearHeading(ActiveMotionValues.getClearToTurnPose())

                    .turn(ActiveMotionValues.getTurnAngle())

                    .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                    .build();

            if (ActiveMotionValues.getSecondPixel() && !trussSideTape) {

                drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                        .waitSeconds(1)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .turn(ActiveMotionValues.getTurnAngle())

                        .waitSeconds(ActiveMotionValues.getStopSecs())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();

            }

            if (!trussSideTape) {

                drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                        .waitSeconds(1)

                        .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                        .lineToLinearHeading(ActiveMotionValues.getClearPose())

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .waitSeconds(ActiveMotionValues.getStopSecs())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();


                drive.trajName = "TSLDCenter";

            }


            drive.trajectoryBuilt = drive.currentTrajSeq != null;
        }


    }

    @Override
    public void execute() {

    }

    @Override
    public void end(boolean interrupted) {
        drive.trajectoryBuilding = false;
    }

    @Override
    public boolean isFinished() {
        return drive.trajectoryBuilt;
    }

}
