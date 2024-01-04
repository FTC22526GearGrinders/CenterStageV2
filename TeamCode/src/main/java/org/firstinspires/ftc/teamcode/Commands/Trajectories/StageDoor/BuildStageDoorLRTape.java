package org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor;

import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
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
    }


    @Override
    public void execute() {


        boolean secondPixel = ActiveMotionValues.getSecondPixel();

        boolean park = !secondPixel;

        if (secondPixel) {


            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .setTurnConstraint(Constants.DriveConstants.MAX_ANG_VEL, Constants.DriveConstants.MAX_ANG_ACCEL)

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

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

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

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
