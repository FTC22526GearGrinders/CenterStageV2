package org.firstinspires.ftc.teamcode;


import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag4LineupPose;
import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag5LineupPose;
import static org.firstinspires.ftc.teamcode.FieldConstantsRed.AprilTagConstants.tag6LineupPose;

import com.acmerobotics.roadrunner.geometry.Pose2d;

public final class FieldConstantsRed {

    public static double startAngle = Math.toRadians(-90);

    public static Pose2d nearPreParkPoseBB = new Pose2d(40, -60, 0);
    public static Pose2d centerPreParkPoseBB = new Pose2d(40, -12, 0);
    public static Pose2d nearParkPoseBB = new Pose2d(55, -60, 0);
    public static Pose2d centerParkPoseBB = new Pose2d(55, -12, 0);
    public static Pose2d nearParkPose = new Pose2d(55, -60, startAngle);
    public static Pose2d centerParkPose = new Pose2d(55, -12, startAngle);

    public static Pose2d nearParkOptionPose = new Pose2d(36, -60, startAngle);

    public static Pose2d centerParkOptionPose = new Pose2d(36, -12, startAngle);

    public static Pose2d nearOptionPose = new Pose2d(36, -60, startAngle);


    public static Pose2d centerOptionPose = new Pose2d(36, -12, startAngle);

    public static Pose2d nearPartnerClearPose = new Pose2d(-12, -60, startAngle);

    public static Pose2d centerPartnerClearPose = new Pose2d(-12, -12, startAngle);

    public static Pose2d stageDoorLineUpPose13 = new Pose2d(-36, -12, startAngle);

    public static Pose2d stageDoorLineUpPose2 = new Pose2d(-48, -12, startAngle);

    public static Pose2d nearTrussLineUpPose = new Pose2d(-36, -60, startAngle);

    public static double turnToTagRadians = Math.toRadians(90);

    public static double pixelRightDropX = 2;
    public static double pixelRightDropY = 3;
    public static double pixelLeftDropX = 2;
    public static double pixelLeftDropY = 3;
    public static double leftClearX = 15;

    public static double pixelCenterDropY = 2;


    public static Pose2d pixelLeftDropPose = new Pose2d(pixelLeftDropX, -Constants.RobotConstants.length / 2 + pixelRightDropY, 0);
    public static Pose2d pixelCenterDropPose = new Pose2d(0, -Constants.RobotConstants.length / 2 + pixelCenterDropY, 0);
    public static Pose2d pixelRightDropPose = new Pose2d(pixelRightDropX, -Constants.RobotConstants.length / 2 + pixelRightDropY, 0);


    public static final class AprilTagConstants {
        public static int leftAtag = 0; //index into arrtay tags ar 1,2,3

        public static int canterAtag = 1;

        public static int rightAtag = 2;

        static final double atagAngle = 0;


        public static final Pose2d tagLookAheadPose = new Pose2d(12 + Constants.RobotConstants.length / 2, 0, Math.toRadians(0));


        public static final Pose2d tagStrafeOffsetPose = new Pose2d(-60.25 + 36, 0, 0);


        // tages 4,5 6
        public static final Pose2d atag4 = new Pose2d(60.25, -30, Math.toRadians(atagAngle));
        public static final Pose2d atag5 = new Pose2d(60.25, -36, Math.toRadians(atagAngle));
        public static final Pose2d atag6 = new Pose2d(60.25, -42, Math.toRadians(atagAngle));

        public static final Pose2d tag4LineupPose = new Pose2d(12, -30, startAngle);

        public static final Pose2d tag5LineupPose = new Pose2d(12, -36, startAngle);

        public static final Pose2d tag6LineupPose = new Pose2d(23.5, -42, startAngle);

    }

    public static Pose2d getActiveTagPose(int num) {
        int sel = num - 4;
        switch (sel) {
            case 0:
                return FieldConstantsRed.AprilTagConstants.atag4;
            case 1:
                return FieldConstantsRed.AprilTagConstants.atag5;
            case 2:
                return FieldConstantsRed.AprilTagConstants.atag6;
            default:
                return FieldConstantsRed.AprilTagConstants.atag4;
        }
    }

    public static Pose2d getActiveTagLineupPose(int num) {
        int sel = num - 4;
        switch (sel) {
            case 0:
                return tag4LineupPose;
            case 1:
                return tag5LineupPose;
            case 2:
                return tag6LineupPose;
            default:
                return FieldConstantsRed.AprilTagConstants.atag4;
        }
    }


    public static final class XPYM {
        //left and right are from the view of the robot


        public static final Pose2d startPos = new Pose2d(12, -Constants.FieldConstants.length / 2
                + Constants.RobotConstants.length / 2, startAngle);
        public static final Pose2d rightTapeMid = new Pose2d(23.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(12, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(0.5, -30);


        public static final Pose2d leftAdvancePose = new Pose2d(12,
                leftTapeMid.getY() - Constants.RobotConstants.length / 2 + pixelLeftDropY, startAngle
        );
        public static final Pose2d rightAdvancePose = new Pose2d(12,
                rightTapeMid.getY() - Constants.RobotConstants.length / 2 + pixelRightDropY, startAngle
        );

        //for red left travel is -X, right travel is +x
        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY(), startAngle)
                .plus(pixelLeftDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY(), startAngle).plus(pixelCenterDropPose);

        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY(), startAngle)
                .plus(pixelRightDropPose);

        public static final Pose2d retLPose = new Pose2d(0, -2);
        public static final Pose2d retRPose = new Pose2d(0, -7.25);
        public static final Pose2d retCPose = new Pose2d(0, -8);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retLPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retRPose);
        public static final Pose2d leftClearPose = leftRetractPose.plus(new Pose2d(leftClearX, 0));

    }

    public static final class XMYM {
        //left and right are from the view of the robot

        //Base layout for adapting to other quadrants


        public static final Pose2d startPose = new Pose2d(-36, -(Constants.FieldConstants.length
                - Constants.RobotConstants.length) / 2, startAngle);


        public static final Pose2d rightTapeMid = new Pose2d(-24.5, -30);
        public static final Pose2d centerTapeMid = new Pose2d(-36, -24.5);
        public static final Pose2d leftTapeMid = new Pose2d(-47.5, -30);

        public static final Pose2d leftAdvancePose = new Pose2d(-36,
                leftTapeMid.getY() - Constants.RobotConstants.length / 2 + pixelLeftDropY, startAngle
        );
        public static final Pose2d rightAdvancePose = new Pose2d(-36,
                rightTapeMid.getY() - Constants.RobotConstants.length / 2 + pixelRightDropY, startAngle
        );

        public static final Pose2d leftDropPose = new Pose2d(leftTapeMid.getX(),
                leftTapeMid.getY(), startAngle)
                .plus(pixelLeftDropPose);
        public static final Pose2d centerDropPose = new Pose2d(centerTapeMid.getX(),
                centerTapeMid.getY(), startAngle)
                .plus(pixelCenterDropPose);

        public static final Pose2d rightDropPose = new Pose2d(rightTapeMid.getX(),
                rightTapeMid.getY(), startAngle)
                .plus(pixelRightDropPose);
        public static final Pose2d retLPose = new Pose2d(0, -2);
        public static final Pose2d retRPose = new Pose2d(0, -2);
        public static final Pose2d retCPose = new Pose2d(0, -8);
        public static final Pose2d leftRetractPose = leftDropPose.plus(retLPose);
        public static final Pose2d centerRetractPose = centerDropPose.plus(retCPose);
        public static final Pose2d rightRetractPose = rightDropPose.plus(retRPose);
        public static final Pose2d clearPose = leftRetractPose.plus(new Pose2d(9.5, 0));

        public static final Pose2d centerSDClearPixelPose = new Pose2d(leftTapeMid.getX(), centerRetractPose.getY(), startAngle);

        public static final Pose2d clearToTurnPose = new Pose2d(36, -55, startAngle);

    }
}