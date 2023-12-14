package org.firstinspires.ftc.teamcode.Commands.Auto;


import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.CV.WhitePixelPipeline;
import org.firstinspires.ftc.teamcode.Commands.Utils.ActiveMotionValues;
import org.firstinspires.ftc.teamcode.Subsystems.Drive_Subsystem;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;


public class DetectWhitePixelsCamOffset extends CommandBase {

    private final CommandOpMode myOpMode;

    OpenCvWebcam webcam;

    WhitePixelPipeline wpp;

    private ElapsedTime et;

    private boolean noEnd;

    boolean cameraOpened;

    boolean cameraClosed;
    private int tst;

    private FtcDashboard dashboard;

    private Drive_Subsystem drive;

    public DetectWhitePixelsCamOffset(CommandOpMode opMode, Drive_Subsystem drive, boolean noEnd) {
        myOpMode = opMode;
        this.drive = drive;
        this.noEnd = noEnd;
    }

    @Override
    public void initialize() {


        dashboard = FtcDashboard.getInstance();

        myOpMode.telemetry = new MultipleTelemetry(myOpMode.telemetry, dashboard.getTelemetry());

        et = new ElapsedTime();

        wpp = new WhitePixelPipeline();

        webcam = OpenCvCameraFactory.getInstance().createWebcam(myOpMode.hardwareMap.get(WebcamName.class, "Webcam 1"));

        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {


            @Override

            public void onOpened() {
                /*
                 * Tell the webcam to start streaming images to us! Note that you must make sure
                 * the resolution you specify is supported by the camera. If it is not, an exception
                 * will be thrown.
                 *
                 * Keep in mind that the SDK's UVC driver (what OpenCvWebcam uses under the hood) only
                 * supports streaming from the webcam in the uncompressed YUV image format. This means
                 * that the maximum resolution you can stream at and still get up to 30FPS is 480p (640x480).
                 * Streaming at e.g. 720p will limit you to up to 10FPS and so on and so forth.
                 *
                 * Also, we specify the rotation that the webcam is used in. This is so that the image
                 * from the camera sensor can be rotated such that it is always displayed with the image upright.
                 * For a front facing camera, rotation is defined assuming the user is looking at the screen.
                 * For a rear facing camera or a webcam, rotation is defined assuming the camera is facing
                 * away from the user.
                 */

                //    webcam.setPipeline(stpb);
                // webcam.setPipeline(stpb);
                //start streaming the camera
                webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);

                webcam.setPipeline(wpp);

                cameraOpened = true;
                cameraClosed = false;
            }

            @Override
            public void onError(int errorCode) {
                /*
                 * This will be called if the camera could not be opened}
                 */
            }
        });


        FtcDashboard.getInstance().startCameraStream(webcam, 5);

    }

    @Override
    public void execute() {
        double pixel1X = wpp.getXValue(1);
        double pixel2X = wpp.getXValue(2);
        int leftPixelIndex = 0;
        int rightPixelindex = 0;
        double leftPixelx = 0;
        double rightPixel = 0;

        if (pixel1X < pixel2X) {
            leftPixelIndex = 1;
            rightPixelindex = 2;
        } else {
            leftPixelIndex = 2;
            rightPixelindex = 1;
        }

        double pixelWidth = 5;
        double cameraXOffxet = 4;

        double pixelsPerInch = wpp.getWidth(1) / pixelWidth;
        double cameraPixelOffset = pixelsPerInch * cameraXOffxet;


        {

            myOpMode.telemetry.addData("Cam1 ", "");
            myOpMode.telemetry.addData("NumContours", wpp.numContours);
            myOpMode.telemetry.addData("LPCTR", wpp.lpctr);
            myOpMode.telemetry.addData("UsableContours", wpp.usableContours);

//                for (int n = 1; n < wpp.usableContours; n++) {
//
//                    myOpMode.telemetry.addData("Area " + String.valueOf(n), wpp.getArea(n));
//
//                    myOpMode.telemetry.addData("XVal " + String.valueOf(n), wpp.getXValue(n));
//
//                    myOpMode.telemetry.addData("YVal " + String.valueOf(n), wpp.getYValue(n));
//                }
            myOpMode.telemetry.update();
        }


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
