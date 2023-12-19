package org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildStageDoorCenterTape extends CommandBase {
    private Drive_Subsystem drive;
    private PixelHandlerSubsystem phss;


    public BuildStageDoorCenterTape(Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;

    }

    @Override
    public void initialize() {

        /**
         * Use th 5 step center for stage door selection
         * <p>
         * It has the pixel delivery after the first step
         */

            if(ActiveMotionValues.getSecondPixel()) {

                drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                        .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                        .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                        .waitSeconds(1)

                        .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                        .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                        .turn(ActiveMotionValues.getTurnAngle())

                        .waitSeconds(ActiveMotionValues.getStopSecs())

                        .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                        .build();
            }

        else
        //park
        {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .waitSeconds(.1)

                    .lineToLinearHeading(ActiveMotionValues.getOptionTargetPose())

                    .build();
        }

        drive.trajName = "TSDCenter";

        drive.trajectoryBuilt = drive.currentTrajSeq != null;
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
