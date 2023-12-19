package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;


public class MoveToPark extends CommandBase {
    private Drive_Subsystem drive;

    private TrajectorySequence parkTraj;

    public MoveToPark(Drive_Subsystem drive) {
        this.drive = drive;
    }

    @Override
    public void initialize() {

        parkTraj = drive.drive.trajectorySequenceBuilder(ActiveMotionValues.getFinalTagPose())

                .lineToLinearHeading(ActiveMotionValues.getPreParkPose())

                .waitSeconds(.1)

                .lineToLinearHeading(ActiveMotionValues.getParkPose())

                .build();

        drive.drive.setPoseEstimate(ActiveMotionValues.getFinalTagPose());
    }

    @Override
    public void execute() {


        drive.drive.followTrajectorySequence(parkTraj);

    }

    @Override
    public void end(boolean interrupted) {
        if (interrupted) {
            drive.drive.stop();
        }
    }

    @Override
    public boolean isFinished() {

        return Thread.currentThread().isInterrupted() || !drive.drive.isBusy() || ActiveMotionValues.getParkPose() == new Pose2d();

    }
}
