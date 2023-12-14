package org.firstinspires.ftc.teamcode.Commands.Drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;

/**
 * detection.ftcPose.x =  detection.rawPose.x;
 * detection.ftcPose.y =  detection.rawPose.z;
 * detection.ftcPose.z = -detection.rawPose.y;
 * <p>
 * double offsetx = detection.ftcPose.x + camerOffsetX;
 * double offsetY = detection.ftcPose.Y + cameraOffsetY;
 * <p>
 * Orientation rot = Orientation.getOrientation(detection.rawPose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, outputUnitsAngle);
 * detection.ftcPose.yaw = -rot.firstAngle;
 * detection.ftcPose.roll = rot.thirdAngle;
 * detection.ftcPose.pitch = rot.secondAngle;
 * <p>
 * detection.ftcPose.range = Math.hypot(detection.ftcPose.x(offsetX), detection.ftcPose.y(offsetY);
 * detection.ftcPose.bearing = outputUnitsAngle.fromUnit(AngleUnit.RADIANS, Math.atan2(-detection.ftcPose.x(offsetX, detection.ftcPose.y(offsetY));
 * detection.ftcPose.elevation = outputUnitsAngle.fromUnit(AngleUnit.RADIANS, Math.atan2(detection.ftcPose.z, detection.ftcPose.y));
 */


public class PositionToBackboardUsingTags extends CommandBase {
    private final Drive_Subsystem drive;
    private int n;
    private boolean tagsSeen;

    private final Vision_Subsystem vss;

    private ElapsedTime et;

    double rangeError;


    public static double max_auto_turn = Constants.DriveConstants.MAX_AUTO_TURN;

    double max_auto_speed = Constants.DriveConstants.MAX_AUTO_SPEED;  //  Clip the approach speed to this max value (adjust for your robot)
    double max_auto_strafe = Constants.DriveConstants.MAX_AUTO_STRAFE;   //  Clip the approach speed to this max value (adjust for your robot)

    double forward = 0;        // Desired forward power/speed (-1 to +1)
    double strafe = 0.5;        // Desired strafe power/speed (-1 to +1)
    double turn = 0.5;

    double headingError;

    double yawError;

    CommandOpMode myOpMode;

    private final boolean noEnd;


    public PositionToBackboardUsingTags(Drive_Subsystem drive, Vision_Subsystem vss, CommandOpMode opMode, boolean noEnd) {
        this.drive = drive;
        this.vss = vss;
        this.noEnd = noEnd;
        myOpMode = opMode;
    }

    @Override
    public void initialize() {

        et = new ElapsedTime();

        n = 5;//ActiveMotionValues.getActTag();

        myOpMode.telemetry.addData("Starting Pos to Tag", "");
        myOpMode.telemetry.update();


    }

    @Override
    public void execute() {


        List<AprilTagDetection> currentDetections = vss.myAprilTagProcessor.getDetections();
        myOpMode.telemetry.addData("Camera State", vss.myVisionPortal.getCameraState());

        myOpMode.telemetry.addData("Camera FPS", vss.myVisionPortal.getFps());
        myOpMode.telemetry.addData("LookingForTag ", n);
        myOpMode.telemetry.addData("# AprilTags Detected", currentDetections.size());
        for (AprilTagDetection detection : currentDetections) {

            if (detection.metadata != null) {

                tagsSeen = true;

                if (detection.id == n) {

                    double cameraOffsetX = 3;
                    double cameraOffsetY = 0;
                    double cameraOffsetZ = 6;

                    double offsetX = detection.ftcPose.x + cameraOffsetX;
                    double offsetY = detection.ftcPose.y + cameraOffsetY;

                    double offsetRange = Math.hypot(offsetX, offsetY);


                    AngleUnit outputUnitsAngle = AngleUnit.DEGREES;

                    Orientation rot = Orientation.getOrientation(detection.rawPose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, outputUnitsAngle);

                    double offsetBearing = outputUnitsAngle.fromUnit(AngleUnit.RADIANS, Math.atan2(-offsetX, offsetY));


               //     headingError = detection.ftcPose.bearing;

                    headingError = offsetBearing;

                    yawError = detection.ftcPose.yaw;

                //    rangeError = detection.ftcPose.range - drive.stopDistanceFromTag;

                          rangeError = offsetRange - drive.stopDistanceFromTag;

                    forward = Range.clip(rangeError * drive.getForwardGain(), -max_auto_speed, max_auto_speed);

                    turn = Range.clip(headingError * drive.getTurn_gain(), -max_auto_turn, max_auto_turn);

                    strafe = Range.clip(-yawError * drive.getStrafe_gain(), -max_auto_strafe, max_auto_strafe);

                    myOpMode.telemetry.addData("Range", detection.ftcPose.range);

                    myOpMode.telemetry.addData("RangeError", rangeError);

                    myOpMode.telemetry.addData("Bearing", detection.ftcPose.bearing);

                    myOpMode.telemetry.addData("BearingError", headingError);


                    myOpMode.telemetry.addData("Yaw", detection.ftcPose.yaw);

                    myOpMode.telemetry.addData("Forward", forward);

                    myOpMode.telemetry.addData("Srafe", strafe);

                    myOpMode.telemetry.addData("Turn", turn);


                    //  moveRobot(forward, strafe, turn);
                }
                if (detection.id != n) {
                    forward = 0;
                    strafe = 0;
                    turn = 0;
                }
                moveRobot(forward, strafe, turn);
            }
        }
        myOpMode.telemetry.update();
    }

    @Override
    public void end(boolean interrupted) {
        myOpMode.telemetry.addData("Ending Pos to Tag", "");
        myOpMode.telemetry.update();
    }

    @Override
    public boolean isFinished() {
        return !noEnd && Math.abs(rangeError) <= .5;
    }

    public void moveRobot(double x, double y, double yaw) {
        // Calculate wheel powers.
        double leftFrontPower = x - y - yaw;
        double rightFrontPower = x + y + yaw;
        double leftBackPower = x + y - yaw;
        double rightBackPower = x - y + yaw;

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower /= max;
            rightFrontPower /= max;
            leftBackPower /= max;
            rightBackPower /= max;
        }

        // Send powers to the wheels.
        drive.drive.setMotorPowers(leftFrontPower, leftBackPower, rightBackPower, rightFrontPower);

    }
}
