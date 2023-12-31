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
    }

    /**
     * Use th 5 step center for stage door selection
     * <p>
     * It has the pixel delivery after the first step
     */
    @Override
    public void execute() {


        boolean secondPixel = ActiveMotionValues.getSecondPixel();
        boolean park = !secondPixel;


        if (secondPixel) {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .waitSeconds(.1)

                    .turn(ActiveMotionValues.getTurnAngle())

                    .waitSeconds(.1)

                    .lineToLinearHeading(ActiveMotionValues.getWaitPartnerClearPose())

                    .waitSeconds(ActiveMotionValues.getStopSecs())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .lineToLinearHeading(ActiveMotionValues.getTargetPose())

                    .build();
        }

        if (park) {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                    .lineToLinearHeading(ActiveMotionValues.getTrussSDLineUpPose())

                    .lineToLinearHeading(ActiveMotionValues.getOptionStopPose())

                    .waitSeconds(.1)

                    .lineToLinearHeading(ActiveMotionValues.getParkPose())

                    .build();
        }

        drive.trajName = "TSDCenter";

        drive.trajectoryBuilt = drive.currentTrajSeq != null;
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
