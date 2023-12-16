package org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class BuildBBLRTraj extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private CommandOpMode opMode;
    private int numberPoints;

    public BuildBBLRTraj(Drive_Subsystem drive, PixelHandlerSubsystem phss, CommandOpMode opMode) {
        this.drive = drive;
        this.phss = phss;
        this.opMode = opMode;
    }

    @Override
    public void initialize() {
        drive.trajectoryBuilt = false;
    }

    @Override
    public void execute() {


        boolean trussSide = ActiveMotionValues.getRedAlliance() && ActiveMotionValues.getLcrpos() == 1
                || !ActiveMotionValues.getRedAlliance() && ActiveMotionValues.getLcrpos() == 3;

        if (trussSide) {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1.)

                    .lineToLinearHeading(ActiveMotionValues.getRetractPose())

                    .lineToLinearHeading(ActiveMotionValues.getClearPose())

                    .lineToLinearHeading(ActiveMotionValues.getTagLineupPose())

                    .turn(ActiveMotionValues.getTurnAngle())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                    .build();

        }

        else

        {

            drive.currentTrajSeq = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getStartPose())

                    .lineToLinearHeading(ActiveMotionValues.getAdvancePose())

                    .lineToLinearHeading(ActiveMotionValues.getDropOffPose())

                    .UNSTABLE_addTemporalMarkerOffset(.5, () -> phss.dropPixel())

                    .waitSeconds(1.)

                    .lineToLinearHeading(ActiveMotionValues.getTagLineupPose())

                    .turn(ActiveMotionValues.getTurnAngle())

                    .lineToLinearHeading(ActiveMotionValues.getPreTagPose())

                    .build();


                   }

        drive.trajName = "BBLeftRight";

        drive.trajectoryBuilt = drive.currentTrajSeq != null;
        opMode.telemetry.addData("Building BBLR", "");
        opMode.telemetry.update();
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
