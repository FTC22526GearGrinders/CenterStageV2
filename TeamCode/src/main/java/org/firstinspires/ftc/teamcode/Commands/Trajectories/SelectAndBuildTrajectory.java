package org.firstinspires.ftc.teamcode.Commands.Trajectories;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBCenterTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Backboard.BuildBBLRTraj;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.BuildStageDoorCenterTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.StageDoor.BuildStageDoorLRTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.BuildTrussCenterTape;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.Truss.BuildTrussLRTape;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.PixelHandlerSubsystem;


public class SelectAndBuildTrajectory extends CommandBase {
    private final Drive_Subsystem drive;
    private final PixelHandlerSubsystem phss;

    private ElapsedTime runTime;

    private final CommandOpMode opmode;
    boolean bbstart = false;
    boolean useSD = false;

    boolean useTruss = false;

    boolean found = false;
    int lcr = 0;

    boolean logTrajectory = false;

    public SelectAndBuildTrajectory(CommandOpMode opMode, Drive_Subsystem drive, PixelHandlerSubsystem phss) {
        this.drive = drive;
        this.phss = phss;
        this.opmode = opMode;

    }

    @Override
    public void initialize() {
        bbstart = ActiveMotionValues.getBBStart();
        useSD = ActiveMotionValues.getUseStageDoor();
        useTruss = !useSD;
        lcr = ActiveMotionValues.getLcrpos();
        if (!bbstart) lcr += 10;
        drive.trajectoryBuilding = false;
        runTime = new ElapsedTime();
        opmode.telemetry.addData("SABTinit", "");
        opmode.telemetry.update();
    }

    @Override
    public void execute() {
        found = false;

        if (lcr == 2) {
            drive.runningTrajName = "BB Center";
            new BuildBBCenterTraj(drive, phss, opmode).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }


        if (lcr == 1 || lcr == 3) {
            drive.runningTrajName = "BB LR";
            new BuildBBLRTraj(drive, phss, opmode).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }


        if (useSD && (lcr == 11 || lcr == 13)) {
            drive.runningTrajName = "SD LR";
            new BuildStageDoorLRTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

        if (useSD && lcr == 12) {
            drive.runningTrajName = "SD Center";
            new BuildStageDoorCenterTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

        if (useTruss && (lcr == 11 || lcr == 13)) {
            drive.runningTrajName = "Truss Center";
            new BuildTrussLRTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

        if (useTruss && lcr == 12) {
            drive.runningTrajName = "Truss LR";
            new BuildTrussCenterTape(drive, phss).schedule();
            drive.trajectoryBuilding = true;
            found = true;
        }

    }

    @Override
    public void end(boolean interrupted) {
        opmode.telemetry.addData("Selected", drive.runningTrajName);
        opmode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return found;
    }
}
