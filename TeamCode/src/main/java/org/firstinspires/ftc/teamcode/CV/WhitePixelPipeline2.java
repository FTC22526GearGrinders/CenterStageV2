package org.firstinspires.ftc.teamcode.CV;


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
public class WhitePixelPipeline2 extends OpenCvPipeline {

    //backlog of frames to average out to reduce noise
    ArrayList<double[]> frameList;

 public int tst;
    public int numContours;
    public int usableContours;


    public int thresh = 191;

    private int lpctr = 0;

    Mat src = new Mat();

    Mat blur = new Mat(src.rows(), src.cols(), src.type(), new Scalar(0));

    Mat grey = new Mat();

    Mat hierarchy = new Mat();

    int imgWidth = 0;

    int imgHeight = 0;


    Scalar blue = new Scalar(7, 197, 235, 255);
    Scalar red = new Scalar(255, 0, 0, 255);
    Scalar green = new Scalar(0, 255, 0, 255);
    Scalar white = new Scalar(255, 255, 255, 255);

    public boolean processing = false;

    List<Double> xvalues = new ArrayList<>();
    List<Double> yvalues = new ArrayList<>();
    List<Double> areas = new ArrayList<>();

    List<Double> widths = new ArrayList<>();

    List<Double> heights = new ArrayList<>();

    public WhitePixelPipeline2() {
        frameList = new ArrayList<>();


        lpctr = 0;

    }


    @Override
    public Mat processFrame(Mat input) {
tst++;
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

        grey = new Mat(blur.rows(), blur.cols(), blur.type());

        Imgproc.cvtColor(blur, grey, Imgproc.COLOR_BGR2GRAY);

        Mat binary = new Mat(blur.rows(), blur.cols(), blur.type(), new Scalar(0));

        Imgproc.threshold(grey, binary, thresh, 255, Imgproc.THRESH_BINARY_INV);

        //Finding Contours
        List<MatOfPoint> contours = new ArrayList<>();

        hierarchy = new Mat();

        Imgproc.findContours(binary, contours, hierarchy, Imgproc.RETR_TREE,
                Imgproc.CHAIN_APPROX_SIMPLE);

        numContours = contours.size();
        usableContours = 0;
        areas.clear();
        xvalues.clear();
        yvalues.clear();
        heights.clear();
        widths.clear();


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

            if (temp.size.area() > 500) {


                areas.add(temp.size.area());
                xvalues.add(temp.center.x);
                yvalues.add(temp.center.y);
                heights.add(temp.size.height);
                widths.add(temp.size.width);

                usableContours++;

                drawRR(temp, red, src);

            }


        }


        if (frameList.size() > 5) {
            frameList.remove(0);
        }


        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

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
            Imgproc.line(mat, points[l], points[(l + 1) % 4], color, 4);
        }
    }

    public double getArea(int n) {
        if (!areas.isEmpty() || areas.size() < n)
            return 0;
        else return areas.get(n);
    }

    public double getXValue(int n) {
        if (!xvalues.isEmpty() || xvalues.size() < n)
            return 0;
        else return xvalues.get(n);
    }

    public double getYValue(int n) {
        if (!yvalues.isEmpty() || yvalues.size() < n)
            return 0;
        else return yvalues.get(n);
    }
    public double getWidth(int n) {
        if (!widths.isEmpty() || widths.size() < n)
            return 0;
        else return widths.get(n);
    }
    public double getHeight(int n) {
        if (!heights.isEmpty() || heights.size() < n)
            return 0;
        else return heights.get(n);
    }
}







