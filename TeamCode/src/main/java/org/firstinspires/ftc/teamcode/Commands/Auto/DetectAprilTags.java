package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.FieldConstantsBlue;
import org.firstinspires.ftc.teamcode.FieldConstantsRed;
import org.firstinspires.ftc.teamcode.Subsystems.Vision_Subsystem;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

import java.util.List;


public class DetectAprilTags extends CommandBase {

    private final CommandOpMode myOpMode;


    private int n;
    private boolean tagsSeen;

    private final Vision_Subsystem vss;

    private ElapsedTime et;

    private boolean noEnd;
    private int tst;

    public DetectAprilTags(CommandOpMode opMode, Vision_Subsystem vss, boolean noEnd) {
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

                    ActiveMotionValues.setDetection(detection);

                    ActiveMotionValues.setAprilTagSeen(true);

                    ActiveMotionValues.setActiveTagDistance(detection.ftcPose.range);

                    if (noEnd) {
                        myOpMode.telemetry.addData("Active Tag", n);
                        myOpMode.telemetry.addData("Tag ID", detection.id);
                        myOpMode.telemetry.addLine();

                      //  myOpMode.telemetry.addData("TagPose", tagPose.toString());


                        myOpMode.telemetry.addLine(String.format("Translation Range: %.2f in", detection.ftcPose.range));

                        myOpMode.telemetry.addLine(String.format("Translation Bearing: %.2f deg",detection.ftcPose.bearing));

                        myOpMode.telemetry.addLine(String.format("Translation Yaw: %.2f deg",detection.ftcPose.yaw));



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
