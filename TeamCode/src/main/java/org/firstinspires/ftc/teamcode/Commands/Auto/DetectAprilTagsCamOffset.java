package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class DetectAprilTagsCamOffset extends CommandBase {

    private final CommandOpMode myOpMode;


    private int n;
    private boolean tagsSeen;

    private final Vision_Subsystem vss;

    private ElapsedTime et;

    private boolean noEnd;
    private int tst;

    public DetectAprilTagsCamOffset(CommandOpMode opMode, Vision_Subsystem vss, boolean noEnd) {
        this.vss = vss;
        myOpMode = opMode;
        this.noEnd = noEnd;
    }

    @Override
    public void initialize() {
        et = new ElapsedTime();
        n = ActiveMotionValues.getActTag();
    }

    @Override
    public void execute() {

        if (noEnd)
            n = ActiveMotionValues.getActTag();

        List<AprilTagDetection> currentDetections = vss.myAprilTagProcessor.getDetections();
        myOpMode.telemetry.addData("Camera State", vss.myVisionPortal.getCameraState());

        myOpMode.telemetry.addData("Camera FPS", vss.myVisionPortal.getFps());
        myOpMode.telemetry.addData("LookingForTag ", n);
        myOpMode.telemetry.addData("# AprilTags Detected", currentDetections.size());
        for (AprilTagDetection detection : currentDetections) {
            if (detection.metadata != null) {
                tagsSeen = true;


                ActiveMotionValues.setAprilTagSeen(false);

                if (detection.id == n) {

                    double cameraOffsetX = vss.getxCameraOffset();
                    double cameraOffsetY = vss.getyCameraOffset();
                    double cameraOffsetZ = vss.getzCameraOffset();


                    AngleUnit outputUnitsAngle = AngleUnit.DEGREES;

                    double offsetX = detection.ftcPose.x + cameraOffsetX;
                    double offsetY = detection.ftcPose.y + cameraOffsetY;
                    double offsetZ = detection.ftcPose.z + cameraOffsetZ;

                    double offsetRange = Math.hypot(offsetX, offsetY);

                    Orientation rot = Orientation.getOrientation(detection.rawPose.R, AxesReference.INTRINSIC, AxesOrder.YXZ, outputUnitsAngle);

                    double offsetBearing = outputUnitsAngle.fromUnit(AngleUnit.RADIANS, Math.atan2(-offsetX, offsetY));

                    double calcBearing = outputUnitsAngle.fromUnit(AngleUnit.RADIANS, Math.atan2(-detection.ftcPose.x, detection.ftcPose.y));


                    ActiveMotionValues.setDetection(detection);

                    ActiveMotionValues.setAprilTagSeen(true);

                    Pose2d camPose = new Pose2d(detection.ftcPose.y, detection.ftcPose.x, Math.toRadians(detection.ftcPose.yaw));
                    Pose2d amvcamPose = new Pose2d(ActiveMotionValues.getDetection().ftcPose.y,
                            ActiveMotionValues.getDetection().ftcPose.x, Math.toRadians(ActiveMotionValues.getDetection().ftcPose.yaw));

                    Pose2d tagPose = new Pose2d();


                    if (noEnd) {

                        myOpMode.telemetry.addData("Tag ID", detection.id);
                        myOpMode.telemetry.addLine();

                        //  myOpMode.telemetry.addData("TagPose", tagPose.toString());
                        myOpMode.telemetry.addLine(String.format("Translation X: %.2f in", detection.ftcPose.x));
                        myOpMode.telemetry.addLine(String.format("OffsetX X: %.2f in", offsetX));

                        myOpMode.telemetry.addLine(String.format("Translation Y: %.2f in", detection.ftcPose.y));
                        myOpMode.telemetry.addLine(String.format("OffsetY: %.2f in", offsetY));
                        myOpMode.telemetry.addLine(String.format("Translation Range: %.2f in", detection.ftcPose.range));
                        myOpMode.telemetry.addLine(String.format("Offset Range: %.2f in", offsetRange));

                        myOpMode.telemetry.addLine(String.format("Translation Bearing: %.2f deg", detection.ftcPose.bearing));
                        myOpMode.telemetry.addLine(String.format("Calc Bearing: %.2f deg", calcBearing));
                        myOpMode.telemetry.addLine(String.format("Offset Bearing: %.2f deg", offsetBearing));

                        myOpMode.telemetry.addLine(String.format("Rotation Yaw: %.2f degrees", detection.ftcPose.yaw));


                        myOpMode.telemetry.addLine();
//                        myOpMode.telemetry.addData("CamFieldPose", camFieldPose.toString());
//                        myOpMode.telemetry.addData("CurrRobotPose", currentRobotPose.toString());
//                        myOpMode.telemetry.addLine();
//                        myOpMode.telemetry.addData("FinalPose", finalTagPose.toString());
//                        myOpMode.telemetry.addLine();
                    }

                }

            } else {
                tagsSeen = false;
            }

            int numTagsseen = currentDetections.size();

        }
        myOpMode.telemetry.update();


    }

    @Override

    public void end(boolean interrupted) {
        myOpMode.telemetry.addData("Ending detect Tags", "");
        myOpMode.telemetry.update();

    }

    @Override
    public boolean isFinished() {
        return !noEnd && et.seconds() > .5 && ActiveMotionValues.getAprilTagSeen();

    }
}
