package org.firstinspires.ftc.teamcode.VisionTest;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

//for dashboard
/*@Config*/
public class WhitePixelPipeline extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;


    private int lcr;
    private int numContours;
    private int usableContours;


    public int thresh = 191;
    int left = 128;
    int right = 217;
    int maskTop = 92;
    int maskBottom = 180;

    private int lpctr = 0;

    Mat src = new Mat();
    Mat dst = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat hsvMat = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));
    Mat filtered = new Mat();

    Mat grey = new Mat();

    Mat hierarchy = new Mat();

    Mat cropped = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));


    public double[] areas = {0, 0, 0, 0, 0};

    int imgWidth = 0;

    int imgHeight = 0;

    Scalar blue = new Scalar(7, 197, 235, 255);
    Scalar red = new Scalar(255, 0, 0, 255);
    Scalar green = new Scalar(0, 255, 0, 255);
    Scalar white = new Scalar(255, 255, 255, 255);

    Telemetry telemetry;

    public WhitePixelPipeline(Telemetry telemetry) {
        frameList = new ArrayList<>();
        this.telemetry = telemetry;

        lpctr = 0;

    }


    @Override
    public Mat processFrame(Mat input) {

        src = input;
        if (src.empty()) {
            return input;
        }

        Imgproc.resize(input, src, new Size(320, 240));

        if (!src.empty()) {
            imgWidth = src.width();

            imgHeight = src.height();

        }

        Imgproc.blur(src, blur, new Size(7, 7));

        Mat gray = new Mat(blur.rows(), blur.cols(), blur.type());

        Imgproc.cvtColor(blur, gray, Imgproc.COLOR_BGR2GRAY);

        Mat binary = new Mat(blur.rows(), blur.cols(), blur.type(), new Scalar(0));

        Imgproc.threshold(gray, binary, thresh, 255, Imgproc.THRESH_BINARY);
        //Finding Contours
        List<MatOfPoint> contours = new ArrayList<>();
        Mat hierarchy = new Mat();

        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);

        numContours = contours.size();
        usableContours = 0;

        List<RotatedRect> rr = new ArrayList<>();

        List<Double> rrxval = new ArrayList<>();
        List<Double> rryval = new ArrayList<>();

        List<Double> rrAreas = new ArrayList<>();

        Point points[] = new Point[4];

        MatOfPoint2f approxCurve = new MatOfPoint2f();
        //For each contour found
        for (int i = 0; i < numContours; i++) {
            //Convert contours(i) from MatOfPoint to MatOfPoint2f
            MatOfPoint2f contour2f = new MatOfPoint2f(contours.get(i).toArray());
            //Processing on mMOP2f1 which is in type MatOfPoint2f
            double approxDistance = Imgproc.arcLength(contour2f, true) * 0.02;

            Imgproc.approxPolyDP(contour2f, approxCurve, approxDistance, true);

            //Convert back to MatOfPoint
            MatOfPoint mp2f = new MatOfPoint(approxCurve.toArray());

            RotatedRect temp = Imgproc.minAreaRect(contour2f);

            if (temp.size.area() > 500  && temp.center.y>100) {

                rr.add(temp);

                rrAreas.add(temp.size.area());

                rrxval.add(temp.center.x);
                rryval.add(temp.center.y);

                usableContours++;

                drawRR(temp,red,src);

            }

        }

        telemetry.addData("NumContours", numContours);
        telemetry.addData("UsableContours", usableContours);

        telemetry.addData("RRSize", rr.size());
        for(int n =1;n<rr.size();n++){
            telemetry.addData("XVal "+ String.valueOf(n),rrxval.get(n));
            telemetry.addData("YVal "+ String.valueOf(n),rryval.get(n));
            telemetry.addData("Area "+ String.valueOf(n),rrAreas.get(n));



        }


        if (frameList.size() > 5) {
            frameList.remove(0);
        }


        telemetry.update();

      //  return binary;
          return src;
        // return hierarchey;
        //return grey;
        // return binary;

    }

    private void drawRR(RotatedRect rr, Scalar color, Mat mat) {
        Point points[] = new Point[4];
        rr.points(points);
        for (int l = 0; l < 4; ++l) {
            Imgproc.line(mat, points[l], points[(l + 1) % 4], color,4);
        }
    }
}







