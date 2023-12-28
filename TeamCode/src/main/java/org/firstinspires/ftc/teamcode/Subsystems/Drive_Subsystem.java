package org.firstinspires.ftc.teamcode.Subsystems;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Commands.Trajectories.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;


public class Drive_Subsystem extends SubsystemBase {

    public TrajectorySequence currentTrajSeq = null;
    public String trajName = "";
    public String runningTrajName = "";
    public boolean trajectoryBuilt;
    public boolean trajectoryBuilding;

    private ElapsedTime runtime = new ElapsedTime();


    public SampleMecanumDrive drive;

    CommandOpMode myOpmode;

    public boolean running = false;

    public boolean started = false;

    public boolean stopped = false;

    private double strafe_gain = Constants.DriveConstants.STRAFE_GAIN;
    private double turn_gain = Constants.DriveConstants.TURN_GAIN;

    private double forward_gain = Constants.DriveConstants.FORWARD_GAIN;

    Pose2d currentPoseEstimate;

    //  Turn Control "Gain".  eg: Ramp up to 25% power at a 25 degree error. (0.25 / 25.0)

    public double stopDistanceFromTag = 8.5;

    public double[] leftPixelData = new double[6];

    public double[] rightPixelData = new double[6];

    public Drive_Subsystem(CommandOpMode opMode) {
        myOpmode = opMode;
        runtime.reset();


        drive = new SampleMecanumDrive(myOpmode.hardwareMap);

        drive.setPoseEstimate(new Pose2d());


        runtime.reset();

    }

    public void periodic() {

        if (drive.getPoseEstimate() != null)

            currentPoseEstimate = drive.getPoseEstimate();
    }

    public void setForwardGain(double gain) {
        forward_gain = gain;
    }

    public double getForwardGain() {
        return forward_gain;
    }


    public double getStrafe_gain() {
        return strafe_gain;
    }

    public void setStrafe_gain(double gain) {
        strafe_gain = gain;
    }

    public double getTurn_gain() {
        return turn_gain;
    }

    public void setTurn_gain(double gain) {
        turn_gain = gain;
    }

    public void setCurrentPose(Pose2d pose) {
        drive.setPoseEstimate(pose);
    }

    public void savePoseFromAuto(Pose2d pose) {
        setCurrentPose(pose);
    }

    public void showtelemetry(Telemetry telemetry) {

        telemetry.update();
    }

}




